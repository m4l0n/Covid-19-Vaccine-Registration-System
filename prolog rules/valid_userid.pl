:- use_module(library(pcre)).

valid_malaysia_nric(NRIC) :-
    re_match("(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))-([0-9]{2})-([0-9]{4})", NRIC).