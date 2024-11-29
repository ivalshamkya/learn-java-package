package com.jobseeker.hrms.candidate.mappers.explore;

import com.jobseeker.hrms.candidate.data.basic.response.explore.ExploreDataReponse;
import org.jobseeker.candidate.Candidate;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ExploreMapperService.class
})
public interface IExploreMapper {

    @Mapping(target = "videoResume.videoResume", qualifiedByName = "addURLVideo")
    @Mapping(source = "videoResume.videoResume", target = "videoResume.link", qualifiedByName = "addURLVideo")
    @Mapping(target = "videoResume.videoThumbnail", qualifiedByName = "addURLVideoThumbnail")
    @Mapping(source = "videoResume.videoThumbnail", target = "videoResume.thumbnail.link", qualifiedByName = "addURLVideoThumbnail")
    @Mapping(target = "photoProfile.link", qualifiedByName = "addURLPhoto")
    @Mapping(source = "fromEmployer", target = "isFromMyPool", qualifiedByName = "setFromMyPool")
    @Mapping(source = "coordinate", target = "distance", qualifiedByName = "getDistance")
    @Mapping(source = ".", target = "position", qualifiedByName = "setPosition")
    ExploreDataReponse toExploreDataReponse(Candidate candidate, @Context List<String> jobFunctionIds, @Context String keyword);

}
