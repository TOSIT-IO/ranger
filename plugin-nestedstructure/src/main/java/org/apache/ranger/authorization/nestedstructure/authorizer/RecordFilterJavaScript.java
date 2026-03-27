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

/**
 * Executes an injected javascript command to determine if the user has access to the selected record
 */
public class RecordFilterJavaScript {

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
