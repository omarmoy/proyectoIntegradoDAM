package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.domain.Activity;
import com.proyectoi.kinetia.domain.ActivityPro;
import com.proyectoi.kinetia.domain.Prueba;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.repositories.IActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final IActivityRepository activityRepository;

    public ActivityService(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAll() {
        List<ActivityModel> activityModels = activityRepository.findAll();
        List<Activity> activities = new ArrayList<>();
        for (ActivityModel activityModel : activityModels) {
            activities.add(new Activity(activityModel));
        }
        return activities;
    }

    public List<Prueba> getPrueba(){
        List<Prueba> pruebas = new ArrayList<>();
        for (ActivityModel activityModel : activityRepository.findAll()) {
            pruebas.add(new Prueba(activityModel));
        }
        return pruebas;
    }


    public Long createActivity(ActivityModel activity) {
        try {
            activityRepository.save(activity);
            return activity.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateActivity(ActivityPro activity) {
        Optional<ActivityModel> activityOptional = activityRepository.findById(activity.getId());
        if (activityOptional.isPresent()) {
            ActivityModel activityEdited = activityOptional.get();
            activityEdited.setTitle(activity.getTitle());
            activityEdited.setDescription(activity.getDescription());
            activityEdited.setPrice(activity.getPrice());
            activityEdited.setDate(activity.getDate());
            activityEdited.setPrice(activity.getPrice());
            activityEdited.setLocation(activity.getLocation());
            activityEdited.setCategory(activity.getCategory());
            activityEdited.setFeatured(activity.getFeatured());
            activityEdited.setVacancies(activity.getVacancies());
            activityRepository.save(activityEdited);
            return true;
        }
        return false;
    }

    public Boolean deleteActivity(Long id) {
        try {
            activityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
