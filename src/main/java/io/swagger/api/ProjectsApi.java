package io.swagger.api;

import java.io.IOException;
import java.math.BigDecimal;
import io.swagger.model.Error;
import io.swagger.model.Feature;
import io.swagger.model.FeatureData;
import io.swagger.model.FeatureId;
import java.util.List;
import io.swagger.model.NewFeatureData;
import io.swagger.model.NewProjectData;
import io.swagger.model.NewRelease;
import io.swagger.model.Plan;
import io.swagger.model.Project;
import io.swagger.model.ProjectData;
import io.swagger.model.Release;
import io.swagger.model.ReleaseData;
import io.swagger.model.Resource;
import io.swagger.model.ResourceData;
import io.swagger.model.ResourceId;
import io.swagger.model.Skill;
import io.swagger.model.SkillData;
import io.swagger.model.SkillId;

import io.swagger.annotations.*;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

@Api(value = "projects", description = "the projects API")
public interface ProjectsApi {

    @ApiOperation(value = "Adds dependencies (to other features) to a feature", notes = "This operation allows adding more dependencies on other features by providing a list with the ids of those features ", response = Feature.class, tags={ "Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Feature not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}/dependencies",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Feature> addDependenciesToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body);


    @ApiOperation(value = "Adds features to a release", notes = "This operation allows adding more features to a given Release by providing the list of the feature ids. ", response = Void.class, tags={ "Releases","Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/features",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> addFeaturesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body);


    @ApiOperation(value = "Adds a new Release to the Project", notes = "This operation allows adding a new Release to a given Project by providing the following parameters: name, description and deadline ", response = Release.class, tags={ "Releases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A new release", response = Release.class),
        @ApiResponse(code = 404, message = "Project not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Release> addNewReleaseToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Parameters of the new Release" ,required=true ) @RequestBody NewRelease body);


    @ApiOperation(value = "Add a new resource to the project", notes = "This operation allows defining a new Resource to be allocated to the given Project. ", response = Resource.class, tags={ "Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A new Resource", response = Resource.class),
        @ApiResponse(code = 404, message = "Project not found", response = Resource.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Resource.class) })
    @RequestMapping(value = "/projects/{projectId}/resources",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Resource> addNewResourceToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Resource parameters" ,required=true ) @RequestBody ResourceData body);


    @ApiOperation(value = "Create a new Skill", notes = "This operation allows defining a new Skill in the context of the given Project. ", response = Skill.class, tags={ "Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A new Skill", response = Skill.class),
        @ApiResponse(code = 404, message = "Project not found", response = Skill.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Skill.class) })
    @RequestMapping(value = "/projects/{projectId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Skill> addNewSkillToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Skill parameters" ,required=true ) @RequestBody SkillData body);


    @ApiOperation(value = "Adds resources to a release", notes = "This operation allows assigning more resources to a given Release by providing a list with the Resource ids. ", response = Release.class, tags={ "Releases","Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Release.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/resources",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Release> addResourcesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Resource ids" ,required=true ) @RequestBody List<ResourceId> body);


    @ApiOperation(value = "Adds required skills to a feature", notes = "This operation allows adding more required skills to a given Feature by providing a list of the Skill ids. ", response = Feature.class, tags={ "Features","Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Feature not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Feature> addSkillsToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body);


    @ApiOperation(value = "Add skills to a resource", notes = "This operation allows assigning more skills to a given Resource by providing a list of the Skill ids. ", response = Resource.class, tags={ "Resources","Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Resource.class),
        @ApiResponse(code = 404, message = "Project or Resource not found", response = Resource.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Resource.class) })
    @RequestMapping(value = "/projects/{projectId}/resources/{resourceId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Resource> addSkillsToResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body);


    @ApiOperation(value = "Cancels the Release Plan", notes = "This operation cancels the Release Plan of the given Release. ", response = Void.class, tags={ "Releases","Plan", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/plan",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> cancelLastReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId);


    @ApiOperation(value = "Create a new Feature", notes = "This operation allows defining a new Feature for a given Project. ", response = Feature.class, tags={ "Features","New in v.2", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A new Feature", response = Feature.class),
        @ApiResponse(code = 400, message = "Already exists feature with that 'code'", response = Feature.class),
        @ApiResponse(code = 404, message = "Project not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/create_one",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Feature> createFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewFeatureData body);


    @ApiOperation(value = "Create a new Project", notes = "This operation allows defining a new Project. ", response = Project.class, tags={ "New in v.2","Projects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A new Project", response = Project.class),
        @ApiResponse(code = 404, message = "Project not found", response = Project.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Project.class) })
    @RequestMapping(value = "/projects",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Project> createProject(@ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewProjectData body);


    @ApiOperation(value = "Delete dependencies (to other features) from a feature", notes = "This operation allows deleting dependencies on other features by providing the ids of those features ", response = Feature.class, tags={ "Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}/dependencies",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Feature> deleteDependenciesFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the features to remove as dependencies", required = true) @RequestParam(value = "featureId2", required = true) List<BigDecimal> featureId2);


    @ApiOperation(value = "Remove a given Feature", notes = "This operation allows removing a given Feature. ", response = Void.class, tags={ "Features","New in v.2", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Feature not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId);


    @ApiOperation(value = "Remove a given Project", notes = "This operation allows removing a given Project. ", response = Void.class, tags={ "Projects","New in v.2", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId);


    @ApiOperation(value = "Remove a given Release.", notes = "This operation allows removing a given Release from the Project. ", response = Void.class, tags={ "Releases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succes", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId);


    @ApiOperation(value = "Remove a given Resource", notes = "This operation removes and deallocates a given Resource from the Project. ", response = Void.class, tags={ "Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Resource not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/resources/{resourceId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId);


    @ApiOperation(value = "Delete resources from a release", notes = "This operation allows deallocating resources from a given Release by providing the ids of those resources. ", response = Release.class, tags={ "Releases","Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Release.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/resources",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Release> deleteResourcesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the resources to remove", required = true) @RequestParam(value = "resourceId", required = true) List<BigDecimal> resourceId);


    @ApiOperation(value = "Remove a given Skill", notes = "This operation allows removing a given Skill from the Project settings. ", response = Void.class, tags={ "Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Skill not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/skills/{skillId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId);


    @ApiOperation(value = "Delete required skills from a feature", notes = "This operation allows deleting required skills from a given Feature by providing the Skill ids. ", response = Feature.class, tags={ "Features","Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Feature> deleteSkillsFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId);


    @ApiOperation(value = "Remove skills from a resource", notes = "This operation allows removing skills from a given Resource by providing the Skill ids. ", response = Resource.class, tags={ "Resources","Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Resource.class),
        @ApiResponse(code = 404, message = "Project or Resource not found", response = Resource.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Resource.class) })
    @RequestMapping(value = "/projects/{projectId}/resources/{resourceId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Resource> deleteSkillsFromResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId);


    @ApiOperation(value = "Get a feature given its ID", notes = "This operation returns the main info of a Feature given its featureID. ", response = Feature.class, tags={ "Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of features", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Feature not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Feature> getFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId);


    @ApiOperation(value = "Get features", notes = "This operation returns a list of Features for a given Project. This list can be filtered by Feature status: pending or scheduled. ", response = Feature.class, responseContainer = "List", tags={ "Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of features", response = Feature.class),
        @ApiResponse(code = 404, message = "Project not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Feature>> getFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "any | pending | scheduled", allowableValues = "ANY, PENDING, SCHEDULED") @RequestParam(value = "status", required = false) String status);


    @ApiOperation(value = "Get Project info", notes = "This operation returns the main info for a given Project: name, resources, effort unit, etc. ", response = Project.class, tags={ "Projects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Project info", response = Project.class),
        @ApiResponse(code = 404, message = "Project not found", response = Project.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Project.class) })
    @RequestMapping(value = "/projects/{projectId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Project> getProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId);


    @ApiOperation(value = "Get project resources", notes = "This operation returns a list of the resources allocated to a given Project. ", response = Resource.class, responseContainer = "List", tags={ "Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of resources", response = Resource.class),
        @ApiResponse(code = 404, message = "Project not found", response = Resource.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Resource.class) })
    @RequestMapping(value = "/projects/{projectId}/resources",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Resource>> getProjectResources(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId);


    @ApiOperation(value = "Get project skills", notes = "This operation retrieves a list of the possible skills required in the given Project. ", response = Skill.class, responseContainer = "List", tags={ "Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of skills", response = Skill.class),
        @ApiResponse(code = 404, message = "Project not found", response = Skill.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Skill.class) })
    @RequestMapping(value = "/projects/{projectId}/skills",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Skill>> getProjectSkills(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId);


    @ApiOperation(value = "Get projects", notes = "This operation retrieves a list of projects. ", response = Project.class, responseContainer = "List", tags={ "New in v.2","Projects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of projects", response = Project.class),
        @ApiResponse(code = 404, message = "Project not found", response = Project.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Project.class) })
    @RequestMapping(value = "/projects",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Project>> getProjects();


    @ApiOperation(value = "Get a release given its ID", notes = "This operation returns the main info of a Release given its releaseID. ", response = Release.class, tags={ "Releases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A Release", response = Release.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Release> getRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the release",required=true ) @PathVariable("releaseId") BigDecimal releaseId);


    @ApiOperation(value = "Get the features scheduled for a given release", notes = "This operation returns a list of the features scheduled for the given Release. ", response = Feature.class, responseContainer = "List", tags={ "Releases","Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of features", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/features",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Feature>> getReleaseFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId);


    @ApiOperation(value = "Gets the current Release Plan", notes = "This operation calculates and then returns a new Release Plan for a given Release. ", response = Plan.class, tags={ "Releases","Plan", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Plan.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Plan.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Plan.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/plan",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Plan> getReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) throws ClientProtocolException;


    @ApiOperation(value = "Get releases", notes = "This operation returns a list with the Releases in a given Project. ", response = Release.class, responseContainer = "List", tags={ "Releases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of releases", response = Release.class),
        @ApiResponse(code = 404, message = "Project not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Release>> getReleases(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId);


    @ApiOperation(value = "Modifies a given feature", notes = "This operation allows modifying some parameters of a given Feature: name, description, effort, deadline and priority. ", response = Feature.class, tags={ "Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Feature.class),
        @ApiResponse(code = 404, message = "Project or Feature not found", response = Feature.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Feature.class) })
    @RequestMapping(value = "/projects/{projectId}/features/{featureId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Feature> modifyFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Feature parameters that can be modified" ,required=true ) @RequestBody FeatureData body);


    @ApiOperation(value = "Modify Project", notes = "This operation allows modyfing some parameters of a given Project: effort unit, number of hours per effor unit and number of hours per week for a full time resource. ", response = Project.class, tags={ "Projects", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Project.class),
        @ApiResponse(code = 404, message = "Project not found", response = Project.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Project.class) })
    @RequestMapping(value = "/projects/{projectId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Project> modifyProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project's parameters that can be modified" ,required=true ) @RequestBody ProjectData body);


    @ApiOperation(value = "Modify a given release", notes = "This operation allows modifying a given Release providing new values for the following parameters: name, description and deadline ", response = Release.class, tags={ "Releases", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Release.class),
        @ApiResponse(code = 404, message = "Project or Release not found", response = Release.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Release.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Release> modifyRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Release parameters that can be modified" ,required=true ) @RequestBody ReleaseData body);


    @ApiOperation(value = "Modify a given Resource", notes = "This operation allows modifying the parameters of a given Resource, namely name, description, and availability. ", response = Resource.class, tags={ "Resources", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Resource.class),
        @ApiResponse(code = 404, message = "Project or Resource not found", response = Resource.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Resource.class) })
    @RequestMapping(value = "/projects/{projectId}/resources/{resourceId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Resource> modifyResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Resource parameters that can be modified" ,required=true ) @RequestBody ResourceData body);


    @ApiOperation(value = "Modify a given Skill", notes = "This operation allows modifying the parameters of a given Skill, namely name and description. ", response = Void.class, tags={ "Skills", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project or Skill not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/skills/{skillId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Skill> modifySkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId,
        @ApiParam(value = "Skill parameters that can be modified" ,required=true ) @RequestBody SkillData body);


    @ApiOperation(value = "Remove features from a relese", notes = "This operation allows removing features from a given Release by providing the Feature ids. ", response = Void.class, tags={ "Releases","Features", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Project|Release|Feauture not found", response = Void.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Void.class) })
    @RequestMapping(value = "/projects/{projectId}/releases/{releaseId}/features",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> removeFeaturesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the features to remove", required = true) @RequestParam(value = "featureId", required = true) List<BigDecimal> featureId);

}
