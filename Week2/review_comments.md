Nice job doing an endpoint with Flask and adding a form

- In your endpoint, you're not using GET at all so I think you can remove 'GET' in the list of methods
- Dont use uppercase in your function definitions: in Python the convention is to use lower_case for modules, functions or methods, CamelCase for classes. 
- In your comparator function: an alternative is to use https://docs.python.org/3.8/library/difflib.html#difflib.SequenceMatcher.ratio, or the Levenstein distance https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Python
- Worth adding a README to Week2
