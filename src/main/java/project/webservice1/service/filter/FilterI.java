package project.webservice1.service.filter;

import project.webservice1.model.BaseModel;

import java.util.Set;

public interface FilterI<T extends BaseModel> {
    Set<T> filter(Set<T> entities);
}
