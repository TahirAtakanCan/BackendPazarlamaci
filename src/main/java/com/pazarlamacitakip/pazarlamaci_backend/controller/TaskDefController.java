package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.TaskDefSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.TaskDefResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.TaskDefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-defs")
@RequiredArgsConstructor
public class TaskDefController {

    private final TaskDefService taskDefService;

    @GetMapping
    public ResponseEntity<List<TaskDefResponse>> getAll() {
        return ResponseEntity.ok(taskDefService.getAllTaskDefs());
    }

    @PostMapping
    public ResponseEntity<TaskDefResponse> create(@RequestBody TaskDefSaveRequest request) {
        return ResponseEntity.ok(taskDefService.createTaskDef(request));
    }
}
