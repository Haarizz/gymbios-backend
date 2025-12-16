// src/main/java/com/gym/controller/MessagingController.java
package com.gym.controller;

import com.gym.entity.Message;
import com.gym.entity.MessageGroup;
import com.gym.entity.MessageTemplate;
import com.gym.service.MessageService;
import com.gym.service.MessageGroupService;
import com.gym.service.MessageTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messaging")
@CrossOrigin(origins = "http://localhost:5173") // ok with your React dev server
public class MessagingController {

    private final MessageTemplateService templateService;
    private final MessageService messageService;
    private final MessageGroupService groupService;

    public MessagingController(MessageTemplateService templateService,
                               MessageService messageService,
                               MessageGroupService groupService) {
        this.templateService = templateService;
        this.messageService = messageService;
        this.groupService = groupService;
    }

    /* ---------- Templates ---------- */

    @GetMapping("/templates")
    public List<MessageTemplate> getTemplates() {
        return templateService.findAll();
    }

    @PostMapping("/templates")
    public MessageTemplate createTemplate(@RequestBody MessageTemplate template) {
        return templateService.create(template);
    }

    @PutMapping("/templates/{id}")
    public MessageTemplate updateTemplate(@PathVariable Long id,
                                          @RequestBody MessageTemplate template) {
        return templateService.update(id, template);
    }

    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        templateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ---------- Messages / History ---------- */

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.findAll();
    }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    /* ---------- Groups (optional – for future UI) ---------- */

    @GetMapping("/groups")
    public List<MessageGroup> getGroups() {
        return groupService.findAll();
    }

    @PostMapping("/groups")
    public MessageGroup createGroup(@RequestBody MessageGroup group) {
        return groupService.create(group);
    }

    @PutMapping("/groups/{id}")
    public MessageGroup updateGroup(@PathVariable Long id,
                                    @RequestBody MessageGroup group) {
        return groupService.update(id, group);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
