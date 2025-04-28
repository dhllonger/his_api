package com.his.controller;

import com.his.pojo.PatiOutVisit;
import com.his.pojo.ResponseMessage;
import com.his.pojo.dto.PatiOutVisitDto;
import com.his.service.IPatiOutVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/outvisit")  
public class PatiOutVisitController {

    @Autowired
    private IPatiOutVisitService patiOutVisitService;

    /**
     * 添加挂号信息
     */
    @PostMapping
    public ResponseMessage add(@Validated @RequestBody PatiOutVisitDto dto) {
    	System.out.println(dto);
        PatiOutVisit newPatiOutVisit = patiOutVisitService.add(dto);
        return ResponseMessage.success(newPatiOutVisit);
    }

    /**
     * 根据ID查询挂号信息
     */
    @GetMapping("/{rid}")
    public ResponseMessage get(@PathVariable String rid) {
        PatiOutVisit patiOutVisit = patiOutVisitService.getPatiOutVisit(rid);
        return ResponseMessage.success(patiOutVisit);
    }
    
    /**
     * 根据挂号医生姓名查询挂号信息
     */
    @GetMapping("/doctor/{doctName}")
    public ResponseMessage findAllByDoctName(@PathVariable String doctName) {
        List<PatiOutVisit> patiOutVisits = patiOutVisitService.findAllByDoctName(doctName);
        System.out.println(patiOutVisits);
        return ResponseMessage.success(patiOutVisits);
    }
    

    /**
     * 修改挂号信息信息
     */
    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody PatiOutVisitDto dto) {
        PatiOutVisit updated = patiOutVisitService.edit(dto);
        return ResponseMessage.success(updated);
    }

    /**
     * 删除挂号信息信息
     */
    @DeleteMapping("/{pid}")
    public ResponseMessage delete(@PathVariable String pid) {
        patiOutVisitService.delete(pid);
        return ResponseMessage.success();
    }


}
