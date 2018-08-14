/**
 * Copyright (C) 2018 user name (user@email.com) the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.feign.client.dto.errors;

/**
 * @author Idan Rozenfeld
 */
public enum ErrorCodes {
    NOT_FOUND,
    REQUEST_NOT_READABLE,
    UNKNOWN,
    MISSING_REQUEST_PARAM,
    DATA_VALIDATION,
    METHOD_NOT_ALLOWED,
    HTTP_MEDIA_TYPE_NOT_SUPPORTED
}
