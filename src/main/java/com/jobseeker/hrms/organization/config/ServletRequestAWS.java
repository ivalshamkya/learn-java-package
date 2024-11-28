package com.jobseeker.hrms.organization.config;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyRequestContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsc.dev.jwt.AuthorizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Component
public class ServletRequestAWS {

	private static HttpServletRequest httpServletRequest;
	private static AuthorizerService authorizerService;

	@Autowired
	public ServletRequestAWS(HttpServletRequest httpServletRequest, AuthorizerService authorizerService) {
		ServletRequestAWS.httpServletRequest = httpServletRequest;
		ServletRequestAWS.authorizerService = authorizerService;
	}

	public static String getSourceApp() {
		return Optional.ofNullable(httpServletRequest.getHeader("x-source-app"))
				.orElseThrow(() -> new NoSuchElementException("Unknown App !"));
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
		System.out.println("RemoteHost: " + getOrigin);
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1") || getOrigin.contains("0:0:0:0:0:0:0:1"))
			companyId = Optional.ofNullable(httpServletRequest.getHeader("company_id"))
					.orElseGet(ServletRequestAWS::getCompanyIdLocalAuthorizer);
//					.orElseThrow(() -> new NoSuchElementException("No Company are provided"));

		return companyId;
	}

	private static String getCompanyIdLocalAuthorizer() {
		String getOrigin = httpServletRequest.getHeader("origin");
		String companyId = null;
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1"))
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
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1"))
			candidateId = Optional.ofNullable(httpServletRequest.getHeader("candidate_id"))
					.orElseGet(ServletRequestAWS::getCandidateIdLocalAuthorizer);

		return candidateId;
	}

	private static String getCandidateIdLocalAuthorizer() {
		String getOrigin = httpServletRequest.getHeader("origin");
		String getToken = httpServletRequest.getHeader("authorization");
		String cleanedToken = getToken.replace("Bearer ", "");
		String candidateId = null;
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1"))
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
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1"))
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
		if (getOrigin.contains("localhost") || getOrigin.equals("127.0.0.1"))
			employeeId = authorizerService.getEmployeeId(cleanedToken, getOrigin);
		return employeeId;
	}
	//</editor-fold>

	public static String getTimezone() {
		return Optional.ofNullable(httpServletRequest.getHeader("x-timezone"))
				.orElse("Asia/Jakarta");
	}

	public static String getLanguage() {
		return Optional.ofNullable(httpServletRequest.getHeader("x-lang"))
				.orElse("en");
	}

	public static String getGeneralLanguage() {
		return Optional.ofNullable(httpServletRequest.getHeader("x-lang"))
			.orElse("all");
	}

	public static String getOrigin() {
		return Optional.ofNullable(httpServletRequest.getHeader("origin"))
				.orElseGet(ServletRequestAWS::originJobseekerLife);
	}

	public static String originJobseekerLife() {
		String sourceApp = getSourceApp();
		String origin = "no_origin_found";
		if (sourceApp.equals("jobseeker.life")) origin = "jobseeker.life";

		return origin;
	}

	public static String getRemoteHost() {
//		return "basic-organization";
		return httpServletRequest.getRemoteHost();
	}
}
