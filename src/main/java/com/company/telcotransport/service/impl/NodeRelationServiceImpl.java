package com.company.telcotransport.service.impl;


import com.company.telcotransport.dto.request.NodeRelationRequestDTO;
import com.company.telcotransport.dto.response.NodeRelationResponseDTO;
import com.company.telcotransport.entity.NodeRelation;
import com.company.telcotransport.entity.Nodes;
import com.company.telcotransport.repository.NodeRelationRepository;
import com.company.telcotransport.repository.NodesRepository;
import com.company.telcotransport.service.inter.NodeRelationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeRelationServiceImpl implements NodeRelationService {

    private final NodeRelationRepository nodeRelationRepository;
    private final NodesRepository nodeRepository;
    private final ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteRelation(Long nodeId) {
        nodeRelationRepository.deleteById(nodeId);
    }

    @Transactional
    @Override
    public NodeRelationResponseDTO getRelation(Long nodeId) {
        return modelMapper.map(nodeRelationRepository.findById(nodeId).orElseThrow(
                () -> new EntityNotFoundException("Relation not found")), NodeRelationResponseDTO.class);


    }


    @Transactional
    @Override
    public NodeRelationResponseDTO createRelation(NodeRelationRequestDTO nodeRelationRequestDTO) {
        Nodes parentNode = nodeRepository.findById(nodeRelationRequestDTO.getParent())
                .orElseThrow(() -> new EntityNotFoundException("Parent node not found"));

        Nodes childNode = nodeRepository.findById(nodeRelationRequestDTO.getChild())
                .orElseThrow(() -> new EntityNotFoundException("Child node not found"));

        if (!entityManager.contains(parentNode)) {
            parentNode = entityManager.merge(parentNode);
        }
        if (!entityManager.contains(childNode)) {
            childNode = entityManager.merge(childNode);
        }

        NodeRelation nodeRelation = new NodeRelation();
        nodeRelation.setParent(parentNode);
        nodeRelation.setChild(childNode);

        NodeRelation savedRelation = nodeRelationRepository.save(nodeRelation);
        return modelMapper.map(savedRelation, NodeRelationResponseDTO.class);
    }

    @Transactional
    @Override
    public NodeRelationResponseDTO updateRelation(Long nodeId, NodeRelationRequestDTO nodeRelationRequestDTO) {

        Nodes parentNode = nodeRepository.findById(nodeRelationRequestDTO.getParent())
                .orElseThrow(() -> new EntityNotFoundException("Parent node not found"));

        Nodes childNode = nodeRepository.findById(nodeRelationRequestDTO.getChild())
                .orElseThrow(() -> new EntityNotFoundException("Child node not found"));

        if (!entityManager.contains(parentNode)) {
            parentNode = entityManager.merge(parentNode);
        }
        if (!entityManager.contains(childNode)) {
            childNode = entityManager.merge(childNode);
        }

        NodeRelation nodeRelation = nodeRelationRepository.getReferenceById(nodeId);
        nodeRelation.setParent(parentNode);
        nodeRelation.setChild(childNode);

        NodeRelation savedRelation = nodeRelationRepository.save(nodeRelation);
        return modelMapper.map(savedRelation, NodeRelationResponseDTO.class);
    }
}
