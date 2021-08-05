package org.hand.hodr.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hand.hodr.app.service.SOLineService;
import org.hand.hodr.config.SwaggerTags;
import org.hand.hodr.domain.entity.SOHeaderLine;
import org.hand.hodr.domain.entity.SOLine;
import org.hand.hodr.domain.repository.SOLineRepository;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API接口
 */
@Api(tags = SwaggerTags.SOLINE)
@RestController("soLineController.v1")
@RequestMapping("/v1/soline")
public class SOLineController extends BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SOLineService soLineService;

//    @ApiOperation(value = "订单行删除API，参数为行ID")
//    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
//    @DeleteMapping("/{soLineId}")
//    public ResponseEntity<Results> deleteSOLineByLineId(@PathVariable Long soLineId) {
//        logger.info("deleteSOLineById(soLineId): "+soLineId);
//        soLineService.deleteSOLineById(soLineId);
//        return Results.success();
//    }

    @ApiOperation(value = "订单行批量删除API，参数为行ID")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @DeleteMapping
    public ResponseEntity<Results> deleteSOLines(@RequestBody List<SOLine> soLines) {
        logger.info("soLines.get(0): "+soLines.get(0).toString());
        soLineService.deleteSOLines(soLines);
        return Results.success();
    }

    @ApiOperation(value = "订单行查询API，参数为头ID，返回so_line表信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<Page<SOLine>> selectSOLineByHeaderId(PageRequest pageRequest, @PathVariable Long soHeaderId) {
        logger.info("selectSOLineByHeaderId Controller");
        Page<SOLine> soLines = soLineService.selectSOLineByHeaderId(pageRequest, soHeaderId);
        return Results.success(soLines);
    }

    @ApiOperation(value = "订单行保存API，参数为so_line实体")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @PostMapping
    public ResponseEntity<List<SOLine>> saveSOHLLine(@RequestBody List<SOLine> soLineList) {
        logger.info("soLineList(0): "+soLineList.get(0).toString());
        soLineService.saveSOHLLine(soLineList);
        return Results.success(soLineList);
    }



}
