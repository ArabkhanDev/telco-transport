package com.company.telcotransport.service.inter;


import com.company.telcotransport.dto.request.NodeRequestDTO;
import com.company.telcotransport.dto.response.NodeResponseDTO;

public interface NodesService {

    int deleteNode(Long nodeId);

    NodeResponseDTO getNode(Long nodeId);

    NodeResponseDTO createNode(NodeRequestDTO nodeRequestDTO);

    NodeResponseDTO updateNode(Long nodeId, NodeRequestDTO nodeRequestDTO);

}
