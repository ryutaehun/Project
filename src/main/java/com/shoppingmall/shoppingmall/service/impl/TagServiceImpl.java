package com.shoppingmall.shoppingmall.service.impl;

import com.shoppingmall.shoppingmall.dto.tag.CreateTagRequest;
import com.shoppingmall.shoppingmall.entity.Project;
import com.shoppingmall.shoppingmall.entity.Tag;
import com.shoppingmall.shoppingmall.exception.already.TagAlreadyExistException;
import com.shoppingmall.shoppingmall.exception.notfound.ProjectNotFoundException;
import com.shoppingmall.shoppingmall.exception.notfound.TagNotFoundException;
import com.shoppingmall.shoppingmall.repository.ProjectRepository;
import com.shoppingmall.shoppingmall.repository.TagRepository;
import com.shoppingmall.shoppingmall.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Transactional
    @Override
    public Tag create(long projectId, CreateTagRequest createTagRequest) {
        Project project = projectRepository.findById(projectId);

        if(tagRepository.existsByName(createTagRequest.getTagName())){
            throw new TagAlreadyExistException(createTagRequest.getTagName());
        }

        Tag tag = new Tag(createTagRequest.getTagName());
        tag.setProject(project);

        return tagRepository.save(tag);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTags(Long projectId) {
        return tagRepository.findAllByProjectId(projectId);
    }

    @Transactional
    @Override
    public void updateTag(Long projectId, Long tagId, CreateTagRequest updatedTag) {
        Tag tag = tagRepository.findByIdAndProjectId(tagId,projectId);
        if(tag == null){
            throw new TagNotFoundException(tagId);
        }
        tag.setName(updatedTag.getTagName());
    }

    @Transactional
    @Override
    public void deleteTag(Long projectId, Long tagId) {
        Tag tag = tagRepository.findByIdAndProjectId(tagId, projectId);
        if(tag == null){
            throw new TagNotFoundException(tagId);
        }
        tagRepository.delete(tag);
    }
}
