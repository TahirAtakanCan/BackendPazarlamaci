package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.TaskDefSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.TaskDefResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.TaskDef;
import com.pazarlamacitakip.pazarlamaci_backend.repository.TaskDefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskDefService {

    private final TaskDefRepository taskDefRepository;

    public List<TaskDefResponse> getAllTaskDefs() {
        return taskDefRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskDefResponse createTaskDef(TaskDefSaveRequest request) {
        TaskDef taskDef = new TaskDef();
        taskDef.setAdi(request.getAdi());
        taskDef.setAciklama(request.getAciklama());
        taskDef.setTutarGirisi(request.getTutarGirisi());

        TaskDef saved = taskDefRepository.save(taskDef);
        return mapToResponse(saved);
    }

    private TaskDefResponse mapToResponse(TaskDef taskDef) {
        return TaskDefResponse.builder()
                .id(taskDef.getId())
                .adi(taskDef.getAdi())
                .aciklama(taskDef.getAciklama())
                .tutarGirisi(taskDef.getTutarGirisi())
                .build();
    }
}
