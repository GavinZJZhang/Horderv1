package org.hand.hodr.api.controller.v1;

import com.google.gson.Gson;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.app.service.ExampleService;
import org.hand.hodr.app.service.SOHeaderPriceService;
import org.hand.hodr.app.service.SOHeaderService;
import org.hand.hodr.config.SwaggerTags;
import org.hand.hodr.domain.entity.*;
import org.hand.hodr.domain.repository.ExampleRepository;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * API接口
 */
@Api(tags = SwaggerTags.SOHEADER)
@RestController("soHeaderController.v1")
@RequestMapping("/v1/soheader")
public class SOHeaderController extends BaseController {

    @Autowired
    private SOHeaderService soHeaderService;

    @Autowired
    private SOHeaderPriceService soHeaderPriceService;


//    /**
//     *
//     * @param selectSOHByIdNumStatDtoStr
//     * @return
//     */
//    @ApiOperation(value = "订单汇总查询API,根据公司ID、客户ID、订单状态精确查询，根据销售订单号模糊查询")
//    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
//    @GetMapping("/select")
//    public ResponseEntity<Page<CCSO>> selectSOHByIdNumStat(PageRequest pageRequest, @RequestParam(value = "id", required = false, defaultValue = "{\"companyId\"=null, \"customerId\"=null, \"orderNumber\"=null, \"orderStatus\"=null}") String selectSOHByIdNumStatDtoStr) {
//        SelectSOHByIdNumStatDto selectSOHByIdNumStatDto = new Gson().fromJson(selectSOHByIdNumStatDtoStr, SelectSOHByIdNumStatDto.class);
//        System.out.println(selectSOHByIdNumStatDto.toString());
//        Page<CCSO> ccsoList = soHeaderService.selectSOHByIdNumStat(pageRequest, selectSOHByIdNumStatDto);
//        return Results.success(ccsoList);
//    }

//    /**
//     *
//     * @param
//     * @return
//     */
//    @ApiOperation(value = "订单汇总查询API,02,根据公司ID、客户ID、订单状态精确查询，根据销售订单号模糊查询")
//    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
//    @GetMapping("/select")
//    public ResponseEntity<Page<CCSO>> selectSOHByIdNumStat02(PageRequest pageRequest, Long companyId, Long customerId, String orderNumber, String orderStatus) {
//        System.out.println("companyId"+companyId);
//        System.out.println("customerId"+customerId);
//        System.out.println("orderNumber"+orderNumber);
//        System.out.println("orderStatus"+orderStatus);
//        SelectSOHByIdNumStatDto selectSOHByIdNumStatDto = new SelectSOHByIdNumStatDto();
//        selectSOHByIdNumStatDto.setCompanyId(companyId);
//        selectSOHByIdNumStatDto.setCustomerId(customerId);
//        selectSOHByIdNumStatDto.setOrderNumber(orderNumber);
//        selectSOHByIdNumStatDto.setOrderStatus(orderStatus);
//        System.out.println(selectSOHByIdNumStatDto.toString());
//        Page<CCSO> ccsoList = soHeaderService.selectSOHByIdNumStat(pageRequest, selectSOHByIdNumStatDto);
//        return Results.success(ccsoList);
//    }

    /**
     *
     * @param
     * @return
     */
    @ApiOperation(value = "订单汇总查询API,02,根据公司ID、客户ID、订单状态精确查询，根据销售订单号模糊查询")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/select")
    public ResponseEntity<Page<SOHeaderVo>> selectSOHByIdNumStat03(PageRequest pageRequest, Long companyId, Long customerId, String orderNumber, String orderStatus, Long soHeaderId) {
        System.out.println("companyId"+companyId);
        System.out.println("customerId"+customerId);
        System.out.println("orderNumber"+orderNumber);
        System.out.println("orderStatus"+orderStatus);
        System.out.println("soHeaderId: "+soHeaderId);
        SelectSOHByIdNumStatDto selectSOHByIdNumStatDto = new SelectSOHByIdNumStatDto();
        selectSOHByIdNumStatDto.setCompanyId(companyId);
        selectSOHByIdNumStatDto.setCustomerId(customerId);
        selectSOHByIdNumStatDto.setOrderNumber(orderNumber);
        selectSOHByIdNumStatDto.setOrderStatus(orderStatus);
        selectSOHByIdNumStatDto.setSoHeaderId(soHeaderId);
        System.out.println(selectSOHByIdNumStatDto.toString());
        Page<SOHeaderVo> soHeaders= soHeaderPriceService.selectSOHByIdNumStat(pageRequest, selectSOHByIdNumStatDto);
        return Results.success(soHeaders);
    }

    @ApiOperation(value = "映射company_id:company_name")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/mapcompony")
    public ResponseEntity<List<Map<String,String>>> mapCompony() {
        Map<String, String> map = new HashMap<>();
        map.put("meaning", "贵州茅台集团");
        map.put("value", "600619");
        List<Map<String,String>> list = new ArrayList();
        list.add(map);
        System.out.println(list.get(0).toString());
        return Results.success(list);
    }

//    /**
//     *
//     * @param soHeaderId
//     * @return
//     */
//    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
//    @GetMapping("/{soHeaderId}")
//    public ResponseEntity<SOHeader> selectSOHeaderByHeaderId(@PathVariable Long soHeaderId) {
//        SOHeader soHeader = soHeaderService.selectSOHeaderByHeaderId(soHeaderId);
//        return Results.success(soHeader);
//    }

    /**
     *
     * @param soHeaderId
     * @return
     */
    @ApiOperation(value = "订单头查询API，返回so_header表信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/selectheader")
    public ResponseEntity<SOHeader> selectSOHeaderByHeaderId(Long soHeaderId) {
        SOHeader soHeader = soHeaderService.selectSOHeaderByHeaderId(soHeaderId);
        return Results.success(soHeader);
    }

    /**
     *
     * @param soHeaderId
     * @return
     */
    @ApiOperation(value = "订单头查询API，返回so_header表和so_line表信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/selectheaderline")
    public ResponseEntity<SOHeaderLine> selectSOHeaderLineByHeaderId(Long soHeaderId) {
        SOHeaderLine soHeaderLine = soHeaderService.selectSOHeaderLineByHeaderId(soHeaderId);
        return Results.success(soHeaderLine);
    }


    /**
     *
     * @param
     * @return
     */
    @ApiOperation(value = "订单删除API，删除订单头和订单行")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @DeleteMapping
    public ResponseEntity<Results> delectSOHeaderByHeader(@RequestBody SOHeader[] soHeaders) {
        for(int i=0; i<soHeaders.length; i++) {
            soHeaderService.delectSOHeaderByHeader(soHeaders[i]);
        }
//        soHeaderService.delectSOHeaderByHeader(soHeaders);
        return Results.success();
    }

    @ApiOperation(value = "订单保存API，保存订单头和订单行")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @PostMapping
    public ResponseEntity<SOHeaderLine> saveSOHeaderLine(@RequestBody SOHeaderLine soHeaderLine) {
        SOHeaderLine soHeaderLineFromDB = soHeaderService.saveSOHeaderLine(soHeaderLine);
        return Results.success(soHeaderLineFromDB);
    }

    @ApiOperation(value = "订单状态API")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @PutMapping("/{orderStatus}")
    public ResponseEntity<Results> updateSOHForOrderStatus(@RequestBody SOHeader soHeader, @PathVariable String orderStatus) {
        soHeaderService.updateSOHForOrderStatus(soHeader, orderStatus);
        return Results.success();
    }


}
