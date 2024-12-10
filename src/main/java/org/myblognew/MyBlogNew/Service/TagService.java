package org.myblognew.MyBlogNew.Service;

import org.myblognew.MyBlogNew.dto.TagDTO;
import org.myblognew.MyBlogNew.mapper.TagMapper;
import org.myblognew.MyBlogNew.model.Tag;
import org.myblognew.MyBlogNew.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = tagMapper.convertToEntity(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.convertToDTO(savedTag);
    }

    @GetMapping
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
            return tags.stream().map(tagMapper::convertToDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public Optional<TagDTO> getTagById(@PathVariable Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (!optionalTag.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(tagMapper.convertToDTO(optionalTag.get()));
    }

    public Optional<TagDTO> updateTag(Long id, TagDTO tagDTO) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (!optionalTag.isPresent()) {
            return Optional.empty();
        }
        Tag tag = tagMapper.convertToEntity(tagDTO);
        tag.setId(id);
        tagRepository.save(tag);
        return Optional.of(tagMapper.convertToDTO(tag));
    }

    public boolean deleteTag(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (!optionalTag.isPresent()) {
            return false;
        }
        tagRepository.delete(optionalTag.get());
        return true;
    }
}
