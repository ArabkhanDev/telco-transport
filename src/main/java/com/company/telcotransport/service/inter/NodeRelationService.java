package com.company.telcotransport.service.inter;


import com.company.telcotransport.dto.request.NodeRelationRequestDTO;
import com.company.telcotransport.dto.response.NodeRelationResponseDTO;

public interface NodeRelationService {

    void deleteRelation(Long nodeId);

    NodeRelationResponseDTO getRelation(Long nodeId);

    NodeRelationResponseDTO createRelation(NodeRelationRequestDTO nodeRequestDTO);

    NodeRelationResponseDTO updateRelation(Long nodeId, NodeRelationRequestDTO nodeRequestDTO);

}
