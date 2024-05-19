package com.company.telcotransport.controller;


import com.company.telcotransport.dto.request.NodeRequestDTO;
import com.company.telcotransport.dto.response.NodeResponseDTO;
import com.company.telcotransport.service.inter.NodesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nodes")
@RequiredArgsConstructor
public class NodesController {

    private final NodesService nodeService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable("id") Long nodeId) {
        nodeService.deleteNode(nodeId);
        return ResponseEntity.ok("Node deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<NodeResponseDTO> getNode(@PathVariable("id") Long nodeId) {
        return ResponseEntity.ok(nodeService.getNode(nodeId));
    }

    @PostMapping("/create")
    public ResponseEntity<NodeResponseDTO> createNode(@RequestBody NodeRequestDTO nodeRequestDTO) {
        return ResponseEntity.ok(nodeService.createNode(nodeRequestDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NodeResponseDTO> updateNode(@PathVariable("id") Long nodeId,
                                                      @RequestBody NodeRequestDTO nodeRequestDTO) {
        return ResponseEntity.ok(nodeService.updateNode(nodeId, nodeRequestDTO));
    }
}

