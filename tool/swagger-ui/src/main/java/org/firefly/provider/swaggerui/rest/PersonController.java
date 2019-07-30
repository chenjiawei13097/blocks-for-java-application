package org.firefly.provider.swaggerui.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@Api(value = "xxxvalue", tags = {"xxxpublic", "xxxuserManagement"}, description = "xxxdescription",
    basePath = "xxxbasePath", position = 3, produces = "xxxproduces", consumes = "xxxconsumes",
    protocols = "xxxprotocols", authorizations = {@Authorization(value = "")}, hidden = false)
public class PersonController {
    @PostMapping("/create")
    @ApiOperation(value = "value", notes = "notes", tags = {"xxxpublic", "public"},
        response = PersonDTO.class, responseContainer = "List",
        responseReference = "responseReference", httpMethod = "POST", position = 4,
        nickname = "nickname", produces = "xxxproduces", consumes = "xxxconsumes",
        protocols = "xxxprotocols", authorizations = {@Authorization(value = "")}, hidden = false)
    public PersonDTO create(
        @RequestBody
            @ApiParam(name = "personCreateRequest", value = "创建人员需要的参数。", defaultValue = "xxxdefaultValue", required = true, example = "{}")
            PersonCreateRequest personCreateRequest) {
        return new PersonDTO();
    }
}
