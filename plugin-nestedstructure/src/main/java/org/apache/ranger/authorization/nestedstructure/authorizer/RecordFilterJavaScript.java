/**
 *
 *
* Copyright 2022 Comcast Cable Communications Management, LLC
*
* Licensed under the Apache License, Version 2.0 (the ""License"");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an ""AS IS"" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or   implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* SPDX-License-Identifier: Apache-2.0
*/

package org.apache.ranger.authorization.nestedstructure.authorizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Executes an injected javascript command to determine if the user has access to the selected record
 */
public class RecordFilterJavaScript {
    private static final Logger logger = LoggerFactory.getLogger(RecordFilterJavaScript.class);

    /**
     * javascript primitive imports that the nashorn engine needs to function properly, e.g., with "includes"
     */
    private static final String NASHORN_POLYFILL_ARRAY_PROTOTYPE_INCLUDES  = "if (!Array.prototype.includes) " +
            "{ Object.defineProperty(Array.prototype, 'includes', { value: function(valueToFind, fromIndex) " +
            "{ if (this == null) { throw new TypeError('\"this\" is null or not defined'); } var o = Object(this); " +
            "var len = o.length >>> 0; if (len === 0) { return false; } var n = fromIndex | 0; " +
            "var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0); " +
            "function sameValueZero(x, y) { return x === y || (typeof x === 'number' && typeof y === 'number' " +
            "&& isNaN(x) && isNaN(y)); } while (k < len) { if (sameValueZero(o[k], valueToFind)) { return true; } k++; }" +
            " return false; } }); }";


    /**
     * This class filter prevents javascript from importing, using or reflecting any java classes
     * Helps keep javascript clean of injections.  It also contains other checks to ensure that injected
     * javascript is reasonably safe.
     */

    static class SecurityFilter {
        /**
         *
          * @param filterExpr the javascript to check if it contains potentially harmful commands
         * @return if this script is likely bad
         */
        boolean containsMalware(String filterExpr){
            //this.engine is the javascript notation for getting access to runtime that is executing the script
            //more checks can be added here
            return filterExpr.contains("this.engine");
        }
    }


    public static boolean filterRow(String user, String filterExpr, String jsonString) {
        return false;
    }
}
