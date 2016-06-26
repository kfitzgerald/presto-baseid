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

import com.facebook.presto.operator.scalar.FunctionAssertions;
import com.facebook.presto.spi.type.Type;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.facebook.presto.spi.type.VarcharType.VARCHAR;

public class TestBase58Functions
{
    private FunctionAssertions functionAssertions;

    @BeforeClass
    public void setUp()
    {
        functionAssertions = new FunctionAssertions().addScalarFunctions(Base58Functions.class);
    }

    @Test
    public void testHexToBase58()
    {
        assertFunction("HEX_TO_BASE58('5748a7e32b5a6a276b8b9339')", VARCHAR, "2eXv1po62foJMFZmr");
    }

    @Test
    public void testHexToBase58WithPrefix()
    {
        assertFunction("HEX_TO_BASE58('5748a7e32b5a6a276b8b9339','PR')", VARCHAR, "PR2eXv1po62foJMFZmr");
    }

    @Test
    public void testBase58ToHex()
    {
        assertFunction("BASE58_TO_HEX('2eXv1po62foJMFZmr')", VARCHAR, "5748a7e32b5a6a276b8b9339");
    }

    @Test
    public void testBase58WithPrefixToHex()
    {
        assertFunction("BASE58_TO_HEX('PR2eXv1po62foJMFZmr','PR')", VARCHAR, "5748a7e32b5a6a276b8b9339");
    }

    private void assertFunction(String projection, Type expectedType, Object expected)
    {
        functionAssertions.assertFunction(projection, expectedType, expected);
    }
}
