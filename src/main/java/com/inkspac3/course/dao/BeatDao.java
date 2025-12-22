package com.inkspac3.course.dao;

import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.Beat;

import java.util.List;

public interface BeatDao{
  List<Beat> findAllAvailable() throws DaoException;
}
