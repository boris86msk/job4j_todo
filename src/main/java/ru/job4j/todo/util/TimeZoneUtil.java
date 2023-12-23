package ru.job4j.todo.util;

import org.springframework.stereotype.Component;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@Component
public class TimeZoneUtil {
    public List<Task> getTasksForTimeZone(List<Task> listTask, User user) {
        for (Task task : listTask) {
            var time = task.getCreated()
                    .atZone(TimeZone.getDefault().toZoneId())
                    .withZoneSameInstant(ZoneId.of(user.getTimezone()))
                    .toLocalDateTime();

            task.setCreated(time);
        }
        return listTask;
    }

    public Task getTaskForTimeZone(Task task, User user) {
        var time = task.getCreated()
                .atZone(TimeZone.getDefault().toZoneId())
                .withZoneSameInstant(ZoneId.of(user.getTimezone()))
                .toLocalDateTime();
        task.setCreated(time);

        return task;
    }
}
