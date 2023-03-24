% Check if a character is an uppercase letter
uppercase_char(Char) :-
    char_type(Char, upper).

% Check if a character is a lowercase letter
lowercase_char(Char) :-
    char_type(Char, lower).

% Check if a character is a number
number_char(Char) :-
    char_type(Char, digit).

% Check if a given string contains at least one uppercase letter
contains_uppercase([H|_]) :-
    uppercase_char(H).
contains_uppercase([_|T]) :-
    contains_uppercase(T).

% Check if a given string contains at least one lowercase letter
contains_lowercase([H|_]) :-
    lowercase_char(H).
contains_lowercase([_|T]) :-
    contains_lowercase(T).

% Check if a given string contains at least one number
contains_number([H|_]) :-
    number_char(H).
contains_number([_|T]) :-
    contains_number(T).

% Check if a password is valid
valid_password(Password) :-
    string_length(Password, Length),
    Length >= 8,
    string_chars(Password, CharList),
    contains_uppercase(CharList),
    contains_lowercase(CharList),
    contains_number(CharList).