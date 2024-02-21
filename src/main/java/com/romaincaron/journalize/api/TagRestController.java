package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.Tag;
import com.romaincaron.journalize.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagRestController {
    private TagService tagService;

    @Autowired
    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getTags()
    {
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> getTag(@PathVariable("id") long id) {
        Tag tag = tagService.getTag(id);
        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> add(@RequestBody Tag tag)
    {
        Tag newTag = tagService.persist(tag);
        return new ResponseEntity<Tag>(newTag, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Tag> update(@RequestBody Tag tag)
    {
        Tag updatedTag = tagService.update(tag);
        return new ResponseEntity<Tag>(updatedTag, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        tagService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
