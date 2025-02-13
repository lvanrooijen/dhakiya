package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.progressreport.dto.GetProgressReport;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressReportService {
  private final ProgressReportRepository progressReportRepository;
  private final TagRepository tagRepository;

  public GetProgressReport getById(Long id) {
    ProgressReport progressReport =
        progressReportRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Tag> tags = tagRepository.findByEnvironment(progressReport.getEnvironment());
    return GetProgressReport.from(progressReport, tags);
  }
}
