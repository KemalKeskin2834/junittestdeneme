package com.kemalkeskin.junittestdeneme.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();

}
