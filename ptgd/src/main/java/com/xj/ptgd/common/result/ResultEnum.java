package com.xj.ptgd.common.result;

 /**
  * ResultEnum
  * @author wkm
  * @since 2018/7/30
  */
public enum ResultEnum {
    DATA_NULL(1, "成功，数据为空"),
    SUCCESS(0, "成功"),

     //100-511为http 状态码

     // --- 1xx 信息类 ---
     http_status_continue(100,"Continue"),
     http_status_switching_protocols(101,"Switching protocols"),

     // --- 2xx 响应成功 ---
     http_status_ok(200,"ok"),
     http_status_created(201,"created"),
     http_status_accepted(202,"Accepted"),
     http_status_non_authoritative_information(203,"Non-Authoritative Information"),
     http_status_no_content(204,"No Content"),
     http_status_reset_content(205,"Reset Content"),

     // --- 3xx重定向类 ---
     http_status_multiple_choices(300,"Multiple Choices"),
     http_status_moved_permanently(301,"Moved Permanently"),
     http_status_found(302,"Found"),
     http_status_see_other(303,"See Other"),
     http_status_not_modified(304,"Not Modified"),
     http_status_use_proxy(305,"Use Proxy"),
     http_status_temporary_redirect(307,"Temporary Redirect"),

     // --- 4xx Client Error ---
    http_status_bad_request(400, "Bad Request"),
    http_status_unauthorized(401, "Unauthorized"),
    http_status_payment_required(402, "Payment Required"),
    http_status_forbidden(403, "Forbidden"),
    http_status_not_found(404, "Not Found"),
    http_status_method_not_allowed(405, "Method Not Allowed"),
    http_status_not_acceptable(406, "Not Acceptable"),
    http_status_proxy_authentication_required(407, "Proxy Authentication Required"),
    http_status_request_timeout(408, "Request Timeout"),
    http_status_conflict(409, "Conflict"),
    http_status_gone(410, "Gone"),
    http_status_length_required(411, "Length Required"),
    http_status_precondition_failed(412, "Precondition Failed"),
    http_status_payload_too_large(413, "Payload Too Large"),
    http_status_uri_too_long(414, "URI Too Long"),
    http_status_unsupported_media_type(415, "Unsupported Media Type"),
    http_status_requested_range_not_satisfiable(416, "Requested range not satisfiable"),
    http_status_expectation_failed(417, "Expectation Failed"),
    http_status_im_a_teapot(418, "I'm a teapot"),
    http_status_unprocessable_entity(422, "Unprocessable Entity"),
    http_status_locked(423, "Locked"),
    http_status_failed_dependency(424, "Failed Dependency"),
    http_status_upgrade_required(426, "Upgrade Required"),
    http_status_precondition_required(428, "Precondition Required"),
    http_status_too_many_requests(429, "Too Many Requests"),
    http_status_request_header_fields_too_large(431, "Request Header Fields Too Large"),

    // --- 5xx Server Error ---
    http_status_internal_server_error(500, "系统错误"),
    http_status_not_implemented(501, "Not Implemented"),
    http_status_bad_gateway(502, "Bad Gateway"),
    http_status_service_unavailable(503, "Service Unavailable"),
    http_status_gateway_timeout(504, "Gateway Timeout"),
    http_status_http_version_not_supported(505, "HTTP Version not supported"),
    http_status_variant_also_negotiates(506, "Variant Also Negotiates"),
    http_status_insufficient_storage(507, "Insufficient Storage"),
    http_status_loop_detected(508, "Loop Detected"),
    http_status_bandwidth_limit_exceeded(509, "Bandwidth Limit Exceeded"),
    http_status_not_extended(510, "Not Extended"),
    http_status_network_authentication_required(511, "Network Authentication Required"),

    // 请求参数错误（code : 10000~19999）
    http_status_request_null(10000,"请求参数错误，请确认后重新请求"),
    http_status_null_message(10001,"不能为空，请确认后重新请求"),
    http_status_date_message(10002,"日期格式错误，格式为 yyyyMMdd"),
    http_status_not_number(10003,"必须是数字类型"),
    http_status_data_fix(10004,"请填写固定值"),
    http_status_data_special(10005,"非法字符"),
    http_status_data_too_short(10006,"长度过短，请确认后重新请求"),
    http_status_data_too_long(10007,"长度过长，请确认后重新请求"),
    http_status_data_compare_number(10008,"数字大小比较"),
    http_status_data_compare_date(10009,"开始日期不能晚于结束日期"),
    http_status_data_not_phone(10010,"请输入11位手机号码"),


     // 业务验证错误（code : 20000~29999）
    http_status_data_null(20000,"数据库无结果"),
    http_status_data_have(20001,"数据已存在"),

    // 业务系统错误（code : 30000~39999）
    http_status_run_mac(1000,"MAC校验不通过!!"),
    http_status_sql_connection(30000,"数据库连接异常"),
    http_status_sql_grammar_mistakes(30001,"数据库语法错误"),
    http_status_sql_primary(30002,"唯一数据重复，请确认数据严谨性"),
    http_status_shell_error(30003,"文件下载失败，请联系管理员手动下载"),

     ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}