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
package net.kevinfitzgerald.presto.baseid.functions;

import com.facebook.presto.operator.Description;
import com.facebook.presto.operator.scalar.ScalarFunction;
import com.facebook.presto.spi.type.StandardTypes;
import com.facebook.presto.type.SqlType;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import net.kevinfitzgerald.presto.baseid.functions.base58.Base58;

public final class Base58Functions
{
    private Base58Functions()
    {
    }

    @Description("converts a hex string to base58")
    @ScalarFunction
    @SqlType(StandardTypes.VARCHAR)
    public static Slice hex_to_base58(@SqlType(StandardTypes.VARCHAR) Slice hex)
    {
        String encoded = Base58.encode(hex.toStringUtf8());
        return Slices.utf8Slice(encoded);
    }

    @Description("converts a hex string to base58 with a prefix")
    @ScalarFunction
    @SqlType(StandardTypes.VARCHAR)
    public static Slice hex_to_base58(@SqlType(StandardTypes.VARCHAR) Slice hex, @SqlType(StandardTypes.VARCHAR) Slice prefix)
    {
        String encoded = Base58.encodeWithPrefix(hex.toStringUtf8(), prefix.toStringUtf8());
        return Slices.utf8Slice(encoded);
    }

    @Description("converts base58 string to hex")
    @ScalarFunction
    @SqlType(StandardTypes.VARCHAR)
    public static Slice base58_to_hex(@SqlType(StandardTypes.VARCHAR) Slice base58)
    {
        String decoded = Base58.decode(base58.toStringUtf8());
        return Slices.utf8Slice(decoded);
    }

    @Description("converts a base58 string with a prefix to hex")
    @ScalarFunction
    @SqlType(StandardTypes.VARCHAR)
    public static Slice base58_to_hex(@SqlType(StandardTypes.VARCHAR) Slice base58, @SqlType(StandardTypes.VARCHAR) Slice prefix)
    {
        String decoded = Base58.decodeWithPrefix(base58.toStringUtf8(), prefix.toStringUtf8());
        return Slices.utf8Slice(decoded);
    }
}
