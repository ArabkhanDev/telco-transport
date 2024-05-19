package com.company.telcotransport.controller;

import com.company.telcotransport.dto.request.NodeRelationRequestDTO;
import com.company.telcotransport.dto.response.NodeRelationResponseDTO;
import com.company.telcotransport.service.inter.NodeRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relations")
@RequiredArgsConstructor
public class NodeRelationController {

    private final NodeRelationService nodeRelationService;

    @GetMapping("/{id}")
    public NodeRelationResponseDTO getRelation(@PathVariable("id") Long nodeId) {
        return nodeRelationService.getRelation(nodeId);
    }

    @PutMapping("/update/{id}")
    public NodeRelationResponseDTO updateRelation(@PathVariable("id") Long nodeId,
                                                  @RequestBody NodeRelationRequestDTO nodeRequestDTO) {
        return nodeRelationService.updateRelation(nodeId,  nodeRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRelation(@PathVariable("id") Long nodeId) {
        nodeRelationService.deleteRelation(nodeId);
    }

    @PostMapping("/create")
    public NodeRelationResponseDTO createRelation(@RequestBody NodeRelationRequestDTO nodeRequestDTO) {
        return nodeRelationService.createRelation(nodeRequestDTO);
    }

}
