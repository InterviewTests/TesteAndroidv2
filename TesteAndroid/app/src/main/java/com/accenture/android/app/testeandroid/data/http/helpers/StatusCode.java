package com.accenture.android.app.testeandroid.data.http.helpers;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatusCode {
    public static String switchStatusCodeToJson(int statusCode) {
        String message = "Erro: Entre em contato com os administradores do sistema.";

        if (statusCode >= 100 && statusCode < 200) {
            String title = "Resposta de informação";
            String description = "Tente novamente mais tarde.";

            if (statusCode == StatusCodeEnum.Continue.value) {
                message = title + " (Continue): " + description;
            } else if (statusCode == StatusCodeEnum.SwitchingProtocols.value) {
                message = title + " (SwitchingProtocols): " + description;
            } else if (statusCode == StatusCodeEnum.EarlyHints.value) {
                message = title + " (EarlyHints): " + description;
            } else {
                message = title + ": " + description;
            }
        }

        if (statusCode >= 200 && statusCode < 300) {
            String title = "Sucesso";

            if (statusCode == StatusCodeEnum.OK.value) {
                message = title + " (OK).";
            } else if (statusCode == StatusCodeEnum.Created.value) {
                message = title + " (Created).";
            } else if (statusCode == StatusCodeEnum.Accepted.value) {
                message = title + " (Accepted).";
            } else if (statusCode == StatusCodeEnum.NonAuthoritativeInformation.value) {
                message = title + " (Non Authoritative Information).";
            } else if (statusCode == StatusCodeEnum.NoContent.value) {
                message = title + " (No Content).";
            } else if (statusCode == StatusCodeEnum.ResetContent.value) {
                message = title + " (Reset Content).";
            } else if (statusCode == StatusCodeEnum.PartialContent.value) {
                message = title + " (Partial Content).";
            } else {
                message = title + ".";
            }
        }

        if (statusCode >= 300 && statusCode < 400) {
            String title = "Redirecionamento";

            if (statusCode == StatusCodeEnum.MultipleChoices.value) {
                message = title + " (Multiple Choices).";
            } else if (statusCode == StatusCodeEnum.MovedPermanently.value) {
                message = title + " (Moved Permanently).";
            } else if (statusCode == StatusCodeEnum.Found.value) {
                message = title + " (Found).";
            } else if (statusCode == StatusCodeEnum.SeeOther.value) {
                message = title + " (See Other).";
            } else if (statusCode == StatusCodeEnum.NotModified.value) {
                message = title + " (Not Modified).";
            } else if (statusCode == StatusCodeEnum.TemporaryRedirect.value) {
                message = title + " (Temporary Redirect).";
            } else if (statusCode == StatusCodeEnum.PermanentRedirect.value) {
                message = title + " (Permanent Redirect).";
            } else {
                message = title + ".";
            }
        }

        if (statusCode >= 400 && statusCode < 500) {
            String title = "Erro do cliente";
            String description = "Entre em contato com os administradores do sistema.";

            if (statusCode == StatusCodeEnum.BadRequest.value) {
                message = title + " (Bad Request): " + description;
            } else if (statusCode == StatusCodeEnum.Unauthorized.value) {
                message = title + " (Unauthorized): " + description;
            } else if (statusCode == StatusCodeEnum.PaymentRequired.value) {
                message = title + " (Payment Required): " + description;
            } else if (statusCode == StatusCodeEnum.Forbidden.value) {
                message = title + " (Forbidden): " + description;
            } else if (statusCode == StatusCodeEnum.NotFound.value) {
                message = title + " (Not Found): " + description;
            } else if (statusCode == StatusCodeEnum.MethodNotAllowed.value) {
                message = title + " (Method Not Allowed): " + description;
            } else if (statusCode == StatusCodeEnum.NotAcceptable.value) {
                message = title + " (Not Acceptable): " + description;
            } else if (statusCode == StatusCodeEnum.ProxyAuthenticationRequired.value) {
                message = title + " (Proxy Authentication Required): " + description;
            } else if (statusCode == StatusCodeEnum.RequestTimeout.value) {
                message = title + " (Request Timeout): " + description;
            } else if (statusCode == StatusCodeEnum.Conflict.value) {
                message = title + " (Conflict): " + description;
            } else if (statusCode == StatusCodeEnum.Gone.value) {
                message = title + " (Gone): " + description;
            } else if (statusCode == StatusCodeEnum.LengthRequired.value) {
                message = title + " (Length Required): " + description;
            } else if (statusCode == StatusCodeEnum.PreconditionFailed.value) {
                message = title + " (Precondition Failed): " + description;
            } else if (statusCode == StatusCodeEnum.PayloadTooLarge.value) {
                message = title + " (Payload Too Large): " + description;
            } else if (statusCode == StatusCodeEnum.URITooLong.value) {
                message = title + " (URI Too Long): " + description;
            } else if (statusCode == StatusCodeEnum.UnsupportedMediaType.value) {
                message = title + " (Unsupported Media Type): " + description;
            } else if (statusCode == StatusCodeEnum.RangeNotSatisfiable.value) {
                message = title + " (Range Not Satisfiable): " + description;
            } else if (statusCode == StatusCodeEnum.ExpectationFailed.value) {
                message = title + " (Expectation Failed): " + description;
            } else if (statusCode == StatusCodeEnum.IAmATeapot.value) {
                message = title + " (I'm a Teapot): " + description;
            } else if (statusCode == StatusCodeEnum.UnprocessableEntity.value) {
                message = title + " (Unprocessable Entity): " + description;
            } else if (statusCode == StatusCodeEnum.TooEarly.value) {
                message = title + " (Too Early): " + description;
            } else if (statusCode == StatusCodeEnum.UpgradeRequired.value) {
                message = title + " (Upgrade Required): " + description;
            } else if (statusCode == StatusCodeEnum.PreconditionRequired.value) {
                message = title + " (Precondition Required): " + description;
            } else if (statusCode == StatusCodeEnum.TooManyRequests.value) {
                message = title + " (Too Many Requests): " + description;
            } else if (statusCode == StatusCodeEnum.RequestHeaderFieldsTooLarge.value) {
                message = title + " (Request Header Fields Too Large): " + description;
            } else if (statusCode == StatusCodeEnum.UnavailableForLegalReasons.value) {
                message = title + " (Unavailable For Legal Reasons): " + description;
            } else {
                message = title + ": " + description;
            }
        }

        if (statusCode >= 500) {
            String title = "Erro do servidor";
            String description = "Tente novamente mais tarde.";

            if (statusCode == StatusCodeEnum.InternalServerError.value) {
                message = title + " (Internal Server Error): " + description;
            } else if (statusCode == StatusCodeEnum.NotImplemented.value) {
                message = title + " (Not Implemented): " + description;
            } else if (statusCode == StatusCodeEnum.BadGateway.value) {
                message = title + " (Bad Gateway): " + description;
            } else if (statusCode == StatusCodeEnum.ServiceUnavailable.value) {
                message = title + " (Service Unavailable): " + description;
            } else if (statusCode == StatusCodeEnum.GatewayTimeout.value) {
                message = title + " (Gateway Timeout): " + description;
            } else if (statusCode == StatusCodeEnum.HTTPVersionNotSupported.value) {
                message = title + " (HTTP Version Not Supported): " + description;
            } else if (statusCode == StatusCodeEnum.VariantAlsoNegotiates.value) {
                message = title + " (Variant Also Negotiates): " + description;
            } else if (statusCode == StatusCodeEnum.InsufficientStorage.value) {
                message = title + " (Insufficient Storage): " + description;
            } else if (statusCode == StatusCodeEnum.LoopDetected.value) {
                message = title + " (Loop Detected): " + description;
            } else if (statusCode == StatusCodeEnum.NotExtended.value) {
                message = title + " (Not Extended): " + description;
            } else if (statusCode == StatusCodeEnum.NetworkAuthenticationRequired.value) {
                message = title + " (Network Authentication Required): " + description;
            } else {
                message = title + ": " + description;
            }
        }

        return message;
    }

    public enum StatusCodeEnum {
        Continue(100),
        SwitchingProtocols(101),
        EarlyHints(103),

        OK(200),
        Created(201),
        Accepted(202),
        NonAuthoritativeInformation(203),
        NoContent(204),
        ResetContent(205),
        PartialContent(206),

        MultipleChoices(300),
        MovedPermanently(301),
        Found(302),
        SeeOther(303),
        NotModified(304),
        TemporaryRedirect(307),
        PermanentRedirect(308),

        BadRequest(400),
        Unauthorized(401),
        PaymentRequired(402),
        Forbidden(403),
        NotFound(404),
        MethodNotAllowed(405),
        NotAcceptable(406),
        ProxyAuthenticationRequired(407),
        RequestTimeout(408),
        Conflict(409),
        Gone(410),
        LengthRequired(411),
        PreconditionFailed(412),
        PayloadTooLarge(413),
        URITooLong(414),
        UnsupportedMediaType(415),
        RangeNotSatisfiable(416),
        ExpectationFailed(417),
        IAmATeapot(418),
        UnprocessableEntity(422),
        TooEarly(425),
        UpgradeRequired(426),
        PreconditionRequired(428),
        TooManyRequests(429),
        RequestHeaderFieldsTooLarge(431),
        UnavailableForLegalReasons(451),

        InternalServerError(500),
        NotImplemented(501),
        BadGateway(502),
        ServiceUnavailable(503),
        GatewayTimeout(504),
        HTTPVersionNotSupported(505),
        VariantAlsoNegotiates(506),
        InsufficientStorage(507),
        LoopDetected(508),
        NotExtended(510),
        NetworkAuthenticationRequired(511);

        public int value;

        StatusCodeEnum(int value) {
            this.value = value;
        }
    }
}
