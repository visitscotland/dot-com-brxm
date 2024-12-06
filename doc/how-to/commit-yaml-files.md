# Working with yaml files in BloomReach

## Changes related to *.meta:order-before*

When Cargo bootstraps development data from YAML files, some transformations are applied to the data. However, the 
inferred configuration is occasionally incorrect, resulting in an invalid configuration being exported to the files. 
This is a common issue with *.meta:order-before*. Although the database itself is not corrupted, warnings are generated 
during launch. Maintaining proper housekeeping of error messages is crucial to identifying actual problems when they 
appear in the logs.

If you change the order of files or folders, your changes might be valid. In such cases, ensure that no two files share 
the same value for *.meta:order-before*.
