package org.example.projectmanagementsoftware.pattern.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Task;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class KanbanDto {

    private Project project;
    private List<Task> todo;
    private List<Task> progress;
    private List<Task> done;
}
