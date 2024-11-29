package com.jobseeker.hrms.candidate.config;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyRequestContext;
import jakarta.servlet.http.HttpServletRequest;
import org.jsc.dev.jwt.AuthorizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ServletRequestAWS {

    private static HttpServletRequest httpServletRequest;
    private static AuthorizerService authorizerService;
    private final GlobalVariable globalVariable;

    @Autowired
    public ServletRequestAWS(HttpServletRequest httpServletRequest, AuthorizerService authorizerService, GlobalVariable globalVariable) {
        ServletRequestAWS.httpServletRequest = httpServletRequest;
        ServletRequestAWS.authorizerService = authorizerService;
        this.globalVariable = globalVariable;
    }

    //<editor-fold desc="Get CompanyID">
    public static String getCompanyId() {
        AwsProxyRequest awsProxyRequest = (AwsProxyRequest) httpServletRequest.getAttribute("com.amazonaws.apigateway.request");
        return Optional.ofNullable(awsProxyRequest)
                .map(AwsProxyRequest::getRequestContext)
                .map(AwsProxyRequestContext::getAuthorizer)
                .map(apiGatewayAuthorizerContext -> apiGatewayAuthorizerContext.getContextValue("company_id"))
                .orElseGet(ServletRequestAWS::getCompanyIdLocalDev);
    }

    private static String getCompanyIdLocalDev() {
        String getOrigin = httpServletRequest.getRemoteHost();
        String companyId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            companyId = Optional.ofNullable(httpServletRequest.getHeader("company_id"))
                    .orElseGet(ServletRequestAWS::getCompanyIdLocalAuthorizer);
//					.orElseThrow(() -> new NoSuchElementException("No Company are provided"));

        return companyId;
    }

    private static String getCompanyIdLocalAuthorizer() {
        String getOrigin = httpServletRequest.getHeader("origin");
        String companyId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            companyId = authorizerService.getCompanyId(getOrigin);
        return companyId;
    }
    //</editor-fold>

    //<editor-fold desc="Get CandidateID">
    public static String getCandidateId() {
        AwsProxyRequest awsProxyRequest = (AwsProxyRequest) httpServletRequest.getAttribute("com.amazonaws.apigateway.request");
        return Optional.ofNullable(awsProxyRequest)
                .map(AwsProxyRequest::getRequestContext)
                .map(AwsProxyRequestContext::getAuthorizer)
                .map(apiGatewayAuthorizerContext -> apiGatewayAuthorizerContext.getContextValue("candidate_id"))
                .orElseGet(ServletRequestAWS::getCandidateIdLocalDev);
    }

    private static String getCandidateIdLocalDev() {
        String getOrigin = httpServletRequest.getRemoteHost();
        String candidateId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            candidateId = Optional.ofNullable(httpServletRequest.getHeader("candidate_id"))
                    .orElseGet(ServletRequestAWS::getCandidateIdLocalAuthorizer);

        return candidateId;
    }

    private static String getCandidateIdLocalAuthorizer() {
        String getOrigin = httpServletRequest.getHeader("origin");
        String getToken = httpServletRequest.getHeader("authorization");
        String cleanedToken = getToken.replace("Bearer ", "");
        String candidateId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            candidateId = authorizerService.getCandidateId(cleanedToken);
        return candidateId;
    }
    //</editor-fold>

    //<editor-fold desc="Get EmployeeID">
    public static String getEmployeeId() {
        AwsProxyRequest awsProxyRequest = (AwsProxyRequest) httpServletRequest.getAttribute("com.amazonaws.apigateway.request");
        return Optional.ofNullable(awsProxyRequest)
                .map(AwsProxyRequest::getRequestContext)
                .map(AwsProxyRequestContext::getAuthorizer)
                .map(apiGatewayAuthorizerContext -> apiGatewayAuthorizerContext.getContextValue("employee_id"))
                .orElseGet(ServletRequestAWS::getEmployeeIdLocalDev);
    }

    private static String getEmployeeIdLocalDev() {
        String getOrigin = httpServletRequest.getRemoteHost();
        String companyId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            companyId = Optional.ofNullable(httpServletRequest.getHeader("employee_id"))
                    .orElseGet(ServletRequestAWS::getEmployeeIdLocalAuthorizer);
//					.orElseThrow(() -> new NoSuchElementException("No Employee are provided"));

        return companyId;
    }

    private static String getEmployeeIdLocalAuthorizer() {
        String getOrigin = httpServletRequest.getHeader("origin");
        String getToken = httpServletRequest.getHeader("authorization");
        String cleanedToken = getToken.replace("Bearer ", "");
        String employeeId = null;
        if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
            employeeId = authorizerService.getEmployeeId(cleanedToken, getOrigin);
        return employeeId;
    }
    //</editor-fold>

    public Long countAge(String date) {
        date = date == null ? globalVariable.getDefaultBirthdate() : date;
        try {
            LocalDate birthdate = LocalDate.parse(date);
            Period period = Period.between(birthdate, LocalDate.now());
            int age = period.getYears();
            return (long) age;
        } catch (DateTimeParseException ex) {
            System.out.println("Error parsing date-time: " + ex.getMessage());
            throw ex;
        }
    }

    public static String getCompanyName() {
        AwsProxyRequest awsProxyRequest = (AwsProxyRequest) httpServletRequest.getAttribute("com.amazonaws.apigateway.request");
        return Optional.ofNullable(awsProxyRequest)
                .map(AwsProxyRequest::getRequestContext)
                .map(AwsProxyRequestContext::getAuthorizer)
                .map(apiGatewayAuthorizerContext -> apiGatewayAuthorizerContext.getContextValue("company_name"))
                .orElse("");
    }

    public static String getLanguage() {
        try {
            String lang = httpServletRequest.getHeader("x-lang");
            return lang.isBlank() ? "id" : lang;
        } catch (Exception ex) {
            return "id";
        }
    }

    public static String getTimezone() {
        return Optional.ofNullable(httpServletRequest.getHeader("x-timezone"))
                .orElse("Asia/Jakarta");
    }

    public static String getSourceApp() {
        return Optional.ofNullable(httpServletRequest.getHeader("x-source-app"))
                .orElseThrow(() -> new NoSuchElementException("Unknown App !"));
    }

    public static String getOrigin() {
        String sourceApp = getSourceApp();
        String origin = Optional.ofNullable(httpServletRequest.getHeader("origin"))
                .orElse("");

        if(origin.isBlank() && !sourceApp.equals("jobseeker.life")) {
            throw new NoSuchElementException("No Origin found");
        }
        return origin;
    }

    public static String getRemoteHost() {
        return Optional.ofNullable(httpServletRequest.getRemoteHost())
                .orElse("");
    }

}