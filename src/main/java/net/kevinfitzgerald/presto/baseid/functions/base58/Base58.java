/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kevinfitzgerald.presto.baseid.functions.base58;

import java.math.BigInteger;

public class Base58
{
    private Base58()
    {
    }

    private static String base58Chars = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"; // Base-58 char library

    public static String encode(String hex)
    {
        String originalHex = hex;

        BigInteger numeric = new BigInteger(hex, 16);
        BigInteger zero = new BigInteger("0");
        BigInteger fifty58 = new BigInteger("58");
        String output = "";

        while (numeric.compareTo(zero) == 1) {
            BigInteger remainder = numeric.mod(fifty58);
            numeric = numeric.divide(fifty58);
            output = base58Chars.charAt(Integer.parseInt(remainder.toString())) + output;
        }

        //leading zeros
        for (int i = 0; i < originalHex.length() && originalHex.substring(i, 2).equals("00"); i += 2) {
            output = "1" + output;
        }

        return output;
    }

    public static String decode(String base58Value)
    {
        String originalBase58 = base58Value;

        // Ignore bogus base58 strings
        if (base58Value.matches("[^1-9A-HJ-NP-Za-km-z]")) {
            return "";
        }

        BigInteger output = new BigInteger("0");
        BigInteger fifty58 = new BigInteger("58");

        for (int i = 0; i < base58Value.length(); i++) {
            int current = base58Chars.indexOf(base58Value.charAt(i));
            output = output.multiply(fifty58).add(new BigInteger(current + ""));
        }

        String hex = output.toString(16);

        // Leading zeros
        for (int ii = 0; ii < originalBase58.length() && originalBase58.charAt(ii) == '1'; ii++) {
            hex = "00" + hex;
        }

        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }

        return hex.toLowerCase();
    }

    public static String encodeWithPrefix(String hex, String prefix)
    {
        return prefix + encode(hex);
    }

    public static String decodeWithPrefix(String baseValue, String prefix)
    {
        return decode(baseValue.substring(prefix.length()));
    }
}
