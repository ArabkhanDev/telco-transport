package com.company.telcotransport.service.impl;


import com.company.telcotransport.dto.request.NodeRequestDTO;
import com.company.telcotransport.dto.response.NodeResponseDTO;
import com.company.telcotransport.entity.Nodes;
import com.company.telcotransport.repository.NodesRepository;
import com.company.telcotransport.service.inter.NodesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NodesServiceImpl implements NodesService {

    private final NodesRepository nodeRepository;
    private final ModelMapper modelMapper;


    @Transactional
    @Override
    public int deleteNode(Long id) {
        Nodes parentEntity = nodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent entity not found with id: " + id));

        int count = nodeRepository.countAllRelatedNodes(id);

        nodeRepository.delete(parentEntity);
        count++;

        log.info("{} node(s) deleted.", count);
        return count;
    }


    @Transactional
    @Override
    public NodeResponseDTO getNode(Long nodeId) {
        return modelMapper.map(nodeRepository.getNodeById(nodeId).
                orElseThrow(() -> new RuntimeException("Node with id " + nodeId + " not found")), NodeResponseDTO.class);
    }

    @Override
    public NodeResponseDTO createNode(NodeRequestDTO nodeRequestDTO) {
        Nodes nodes = new Nodes();
        nodes.setName(nodeRequestDTO.getName());
        return modelMapper.map(nodeRepository.save(nodes), NodeResponseDTO.class);
    }

    @Transactional
    @Override
    public NodeResponseDTO updateNode(Long nodeId, NodeRequestDTO nodeRequestDTO) {
        Nodes nodes = nodeRepository.getReferenceById(nodeId);
        nodes.setName(nodeRequestDTO.getName());
        return modelMapper.map(nodeRepository.save(nodes), NodeResponseDTO.class);
    }



}
