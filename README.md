# Presto-BaseId

Provides functions to convert hexadecimal strings to Base58 strings and vice versa.

## `hex_to_base58(hexString[, prefix])`
Converts a hexadecimal string to Base58, with an optional prefix.

Examples:
`SELECT HEX_TO_BASE58('54f86a939e5bc3d6278757c5'); -- 2c146ysDvR8oK2Li8`
`SELECT HEX_TO_BASE58('54f86a939e5bc3d6278757c5', 'PR'); -- PR2c146ysDvR8oK2Li8`


## `base58_to_hex(base58String[, prefix])`
Converts a Base58 string to a hexadecimal string.
If given an optional `prefix`, the value will be stripped from the base58 string.

Examples:
`SELECT BASE58_TO_HEX('2c146ysDvR8oK2Li8'); -- 54f86a939e5bc3d6278757c5`
`SELECT BASE58_TO_HEX('PR2c146ysDvR8oK2Li8', 'PR'); -- 54f86a939e5bc3d6278757c5`
