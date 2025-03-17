package com.jobportal.utility;

/**
 * Utility class containing helper methods for generating email content.
 */
public class Data {

    /**
     * Generates an HTML email body for OTP verification messages.
     * Creates a responsive, styled email template with the user's name and OTP code.
     * 
     * @param user The name/email of the user receiving the OTP
     * @param OTP The one-time password code to be included in the email
     * @return A formatted HTML string containing the complete email body
     */
    public static String getMessageBody(String user,String OTP){
        return "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>OTP Verification</title>\n" +
        "    <style>\n" +
        "        body {\n" +
        "            font-family: Arial, sans-serif;\n" +
        "            margin: 0;\n" +
        "            padding: 0;\n" +
        "            background-color: #f4f7fa;\n" +
        "            color: #333;\n" +
        "        }\n" +
        "        .email-container {\n" +
        "            width: 100%;\n" +
        "            max-width: 600px;\n" +
        "            margin: 0 auto;\n" +
        "            background-color: #ffffff;\n" +
        "            border-radius: 8px;\n" +
        "            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n" +
        "        }\n" +
        "        .email-header {\n" +
        "            background-color: #0056b3;\n" +
        "            color: #ffffff;\n" +
        "            padding: 20px;\n" +
        "            text-align: center;\n" +
        "            border-radius: 8px 8px 0 0;\n" +
        "        }\n" +
        "        .email-header h1 {\n" +
        "            margin: 0;\n" +
        "            font-size: 24px;\n" +
        "        }\n" +
        "        .email-body {\n" +
        "            padding: 20px;\n" +
        "            text-align: center;\n" +
        "        }\n" +
        "        .otp {\n" +
        "            font-size: 32px;\n" +
        "            font-weight: bold;\n" +
        "            color: #0056b3;\n" +
        "        }\n" +
        "        .email-footer {\n" +
        "            background-color: #f1f1f1;\n" +
        "            text-align: center;\n" +
        "            padding: 15px;\n" +
        "            font-size: 14px;\n" +
        "            color: #777;\n" +
        "            border-radius: 0 0 8px 8px;\n" +
        "        }\n" +
        "        .footer-link {\n" +
        "            color: #0056b3;\n" +
        "            text-decoration: none;\n" +
        "        }\n" +
        "        .footer-link:hover {\n" +
        "            text-decoration: underline;\n" +
        "        }\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <div class=\"email-container\">\n" +
        "        <div class=\"email-header\">\n" +
        "            <h1>OTP Verification for Clover</h1>\n" +
        "        </div>\n" +
        "        <div class=\"email-body\">\n" +
        "            <h2>Dear "+ user +",</h2>\n" +
        "            <p>We received a request to verify your email for your Clover account. Please use the following One-Time Password (OTP) to complete your verification:</p>\n" +
        "            <p class=\"otp\">"+OTP+"</p>\n" +
        "            <p>This OTP is valid for 10 minutes. If you did not request this, please disregard this message.</p>\n" +
        "            <p>If you have any questions or need assistance, feel free to <a href=\"mailto:support@Clover.com\" class=\"footer-link\">contact our support team</a>.</p>\n" +
        "        </div>\n" +
        "        <div class=\"email-footer\">\n" +
        "            <p>&copy; 2025 Clover. All rights reserved.</p>\n" +
        "            <p><a href=\"https://www.jobportal.com/terms\" class=\"footer-link\">Terms of Service</a> | <a href=\"https://www.jobportal.com/privacy\" class=\"footer-link\">Privacy Policy</a></p>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</body>\n" +
        "</html>";
    
    }
}