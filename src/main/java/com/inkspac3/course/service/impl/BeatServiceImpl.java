package com.inkspac3.course.service.impl;

import com.inkspac3.course.dao.BeatDao;
import com.inkspac3.course.dao.impl.BeatDaoImpl;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Beat;
import com.inkspac3.course.service.BeatService;

import java.util.List;

public class BeatServiceImpl implements BeatService {
  private final BeatDao beatDao = new BeatDaoImpl();

  @Override
  public List<Beat> getAvailableBeats() throws ServiceException {
    try {
      return beatDao.findAllAvailable();
    } catch (DaoException e) {
      throw new ServiceException("Failed to load beats", e);
    }
  }
}
