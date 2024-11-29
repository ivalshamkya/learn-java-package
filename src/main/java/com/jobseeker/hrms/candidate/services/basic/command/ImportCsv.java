package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.config.appHandler.RequestHandler;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateImportRequest;
import com.jobseeker.hrms.candidate.data.basic.request.ImportCandidateRequest;
import com.jobseeker.hrms.candidate.helper.S3Helper;
import com.jobseeker.hrms.candidate.mappers.ImportCSVMapper;
import com.jobseeker.hrms.candidate.mappers.MasterMapper;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import com.jobseeker.hrms.candidate.repository.master.ICityRepository;
import com.jobseeker.hrms.candidate.repository.master.IDistrictRepository;
import com.jobseeker.hrms.candidate.repository.master.IProvinceRepository;
import com.jobseeker.hrms.candidate.repository.master.ISubDistrictRepository;
import com.jobseeker.hrms.candidate.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.master.area.City;
import org.jobseeker.master.area.District;
import org.jobseeker.master.area.Province;
import org.jobseeker.master.area.SubDistrict;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImportCsv {

    private final ICandidateRepository candidateRepository;
    private final ICityRepository cityRepository;
    private final CompanyRepository companyRepository;
    private final IDistrictRepository districtRepository;
    private final IProvinceRepository provinceRepository;
    private final ISubDistrictRepository subDistrictRepository;

    private final ImportCSVMapper csvMapper;
    private final MasterMapper masterMapper;

    private final UserService userService;

    private final S3Helper s3Helper;

    public List<Candidate> execute(Map<Object, Object> request) throws IOException {
        CandidateImportRequest dataRequest = RequestHandler.remapToData(request, CandidateImportRequest.class);
        InputStreamReader csvResult = s3Helper.readStreamFileFromS3(dataRequest.getUrl());

        List<Candidate> candidates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(csvResult);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {

                // <editor-fold defaultstate="collapsed" desc="setImportCsvRequest">
                ImportCandidateRequest importRequest = ImportCandidateRequest.builder()
                        .Name(record.get("Nama"))
                        .IdentityNumber(record.get("NIK"))
                        .Password(record.get("Password"))
                        .Gender(record.get("Jenis Kelamin"))
                        .Dob(record.get("Tanggal Lahir"))
                        .Email(record.get("Email"))
                        .PhoneNumber(record.get("Nomor Whatsapp"))
                        .AddressIdentity(record.get("Alamat (KTP)"))
                        .PostalCodeIdentity(record.get("Kode POS (KTP)"))
                        .Address(record.get("Alamat (Domisili)"))
                        .PostalCode(record.get("Kode POS (Domisili)"))
                        .Disability(record.get("Disabilitas"))
                        .LastEducation(record.get("Pendidikan Terakhir"))
                        .GPA(record.get("IPK/Nilai"))
                        .StartDate(record.get("Tanggal Mulai"))
                        .GraduationDate(record.get("Tanggal Lulus"))
                        .PositionName(record.get("Nama Posisi"))
                        .JobLevel(record.get("Level Pekerjaan"))
                        .Industry(record.get("Industri"))
                        .StartDateWorking(record.get("Tanggal Mulai Kerja"))
                        .EndDateWorking(record.get("Tanggal Selesai Kerja"))
                        .JobDescription(record.get("Deskripsi Pekerjaan"))
                        .Mobility(record.get("Mobilitas"))
                        .CompanyId(ServletRequestAWS.getCompanyId())
                        .build();
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="setRegionIdentity">
                // Set Province Identity
                Province getProvinceIdentity = provinceRepository.findByProvinceName(importRequest.getProvinceIdentity().getName());

                GeneralDataEmbed provinceIdentity = new GeneralDataEmbed().builder()
                        ._id(getProvinceIdentity.get_id())
                        .name(getProvinceIdentity.getName())
                        .build();

                // Set City Identity
                City getCityIdentity = cityRepository.findByProvinceIdAndCityName(getProvinceIdentity.get_id(), record.get("Kota (KTP)"));
                CityDataEmbed cityIdentity = new CityDataEmbed().builder()
                        ._id(getCityIdentity.get_id())
                        .name(getCityIdentity.getName())
                        .coordinate(getCityIdentity.getCoordinate())
                        .build();

                // Set District Identity
                District getDistrictIdentity = districtRepository.findByCityIdAndDistrictName(getCityIdentity.get_id(), record.get("Kecamatan (KTP)"));
                GeneralDataEmbed districtIdentity = new GeneralDataEmbed().builder()
                        ._id(getDistrictIdentity.get_id())
                        .name(getDistrictIdentity.getName())
                        .build();

                // Set Sub District Identity
                SubDistrict getSubDistrictIdentity = subDistrictRepository.findByDistrictIdAndSubDistrictName(getDistrictIdentity.get_id(), record.get("Kelurahan (KTP)"));
                GeneralDataEmbed subDistrictIdentity = new GeneralDataEmbed().builder()
                        ._id(getSubDistrictIdentity.get_id())
                        .name(getSubDistrictIdentity.getName())
                        .build();

                // Set Region Identity
                importRequest.setProvinceIdentity(provinceIdentity);
                importRequest.setCityIdentity(cityIdentity);
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="setRegionDomicile">
                // Set Province Domicile
                Province getProvince = provinceRepository.findByProvinceName(record.get("Provinsi (Domisili)"));

                GeneralDataEmbed provinceDomicile = new GeneralDataEmbed().builder()
                        ._id(getProvince.get_id())
                        .name(getProvince.getName())
                        .build();

                // Set City Identity
                City getCity = cityRepository.findByProvinceIdAndCityName(getProvince.get_id(), record.get("Kota (Domisili)"));
                CityDataEmbed cityDomicile = new CityDataEmbed().builder()
                        ._id(getCity.get_id())
                        .name(getCity.getName())
                        .coordinate(getCity.getCoordinate())
                        .build();

                // Set District Identity
                District getDistrict = districtRepository.findByCityIdAndDistrictName(getCity.get_id(), record.get("Kecamatan (Domisili)"));
                GeneralDataEmbed districtDomicile = new GeneralDataEmbed().builder()
                        ._id(getDistrict.get_id())
                        .name(getDistrict.getName())
                        .build();

                // Set Sub District Identity
                SubDistrict getSubDistrict = subDistrictRepository.findByDistrictIdAndSubDistrictName(getDistrict.get_id(), record.get("Kelurahan (Domisili)"));
                GeneralDataEmbed subDistrictDomicile = new GeneralDataEmbed().builder()
                        ._id(getSubDistrict.get_id())
                        .name(getSubDistrict.getName())
                        .build();

                // Set Region Identity
                importRequest.setProvince(provinceDomicile);
                importRequest.setCity(cityDomicile);
                // </editor-fold>

                Candidate model = csvMapper.toSaveCandidate(importRequest);

                // <editor-fold defaultstate="collapsed" desc="setFromEmployer">
                // Get Company
                Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                        .orElseThrow(() -> new IllegalArgumentException("Company not found"));

                List<CompanyDataEmbed> fromEmployer = new ArrayList<>();
                fromEmployer.add(masterMapper.toAttachCompany(company));
                model.setFromEmployer(fromEmployer);
                // </editor-fold>

                Candidate candidate = candidateRepository.save(model);
                userService.addNewUserDependtOnCandidate(candidate, importRequest.getPassword());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return candidates;
    }
}
