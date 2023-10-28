% gcd(A: element, B: element, R: result)
% (i,i,o)
gcd(A, B, Result) :-
    B = 0,
    Result is A,
    !.
gcd(A, B, Result) :-
    B \= 0,
    Rem is A mod B,
    gcd(B, Rem, Result).


%lcm(Number,Sol)
%lcm(i,o)
lcm(A,B,R) :-               P is A*B,
                            gcd(A,B,G),
                            R is P div G.



%lcm_list(L: list, R: result)
%lcm_list(i,i,o)
lcm_list([],LCM,LCM).
lcm_list([H|T],CurrentLCM, LCM) :- lcm(CurrentLCM,H,NewLCM),
                                    lcm_list(T, NewLCM, LCM).

%sol1(Numbers: list, Rez: result)
%sol1(i,o)
sol1(Numbers,Rez) :- lcm_list(Numbers,1,Rez).









