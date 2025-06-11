package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.progressreport.dto.GetProgressReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.PROGRESS_REPORTS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class ProgressReportController {
  private final ProgressReportService progressReportService;

  @GetMapping("/{id}")
  public ResponseEntity<GetProgressReport> getById(@PathVariable Long id) {
    GetProgressReport progressReport = progressReportService.getById(id);
    return ResponseEntity.ok(progressReport);
  }
}
