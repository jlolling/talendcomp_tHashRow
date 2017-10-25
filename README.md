# Talend component tHashRow
This component creates a hash value. All configured input columns will be concateneded with the given delimiter. Every configured hash input manipulation will be applied, before the hash will be calculated and populated to the selected output column.

# Basic Settings

## Basic configuration 
### Hash type
Algorithm that will be used to generate the hash 

### Hash output
Column of output schema in which the hash value will be written

## Hash input manipulation

### Relevant fields

|Column|Use|Trim|Case Sensitive|
|------|---|----|--------------|
|List of input columns| Check if column should added to the hash| Check if column should be trimmed| Select if column should be upper case, lower case, case sensitive or not in use (eg. in case of numeric values)

### Delimiter
Delimter to seperate input values

### Null replacement
Value that will be used to calculate the hash, if input value is null
- Example if replacement value is set to "#NULL#""
    1. COLUMN_1 = "Test"
    + COLUMN_2 = null
    + COLUMN_3 = 123
    + Hash Input results in "Test";#NULL#;123 

### Fraction size (float)
Maximum precision of float values

### Fraction size (double)
Maximum precision of double values

### Number format
List of available number formats. Grouping is gernerally disabled. 

### Date format
#### Format date as miliseconds
- if checked
    + all date fields will be represented as miliseconds since unix epoch
- if unchecked
    + all date fields will be represented in the given format 

### Enable string quoting
String based fields will be surrounded with the given quotation mark

### Cut of empty trialing hash base values
If checked all empty trailing values will be truncated before hash will be calculated
- Example without quoting
    + Hash Input = CUSTOMER A;1234;STREET 1;;;
    + results in CUSTOMER A;1234;STREET 1
- Example with quoting
    + Hash Input = "CUSTOMER A";1234;"STREET 1";"";;""
    + results also in CUSTOMER A;1234;STREET 1

# Advanced Settings

## Hash output manipulation
### Modify hash output
If all input values are null, the hash value will be replaced with the given value 
- Example
    + Hash input = ;;;;;
    + Hash value = <given_value>
- Example
    + Hash input = ;;;;1;
    + Hash value = <hash value for this input>

## Additional settings
### Expose hash base
If checked the hash base (concatenation of all input values) will be exposed to the selected column

