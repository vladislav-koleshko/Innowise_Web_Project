package com.inkspac3.course.service;

import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Beat;

import java.util.List;

public interface BeatService {
  List<Beat> getAvailableBeats() throws ServiceException;
}
