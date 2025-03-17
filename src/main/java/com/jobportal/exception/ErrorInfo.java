package com.jobportal.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing error information in the job portal system.
 * This class encapsulates details about errors that occur during application execution.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    /** Descriptive message explaining the error */
    private String errorMessage;

    /** Numeric code identifying the type of error */
    private Integer errorCode;

    /** Timestamp when the error occurred */
    private LocalDateTime timeStamp;
}
