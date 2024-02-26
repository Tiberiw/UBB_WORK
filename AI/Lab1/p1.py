"""
Să se determine ultimul (din punct de vedere alfabetic) cuvânt care poate apărea într-un 
text care conține mai multe cuvinte separate prin ” ” (spațiu). 
De ex. ultimul (dpdv alfabetic) cuvânt din ”Ana are mere rosii si galbene” este cuvântul "si".
"""

import time


def get_last(string):
    words = string.split(" ")
    last = words[0]
    for word in words:
        if word > last:
            last = word

    return last


def test():
    string1 = "Ana are mere orii si galbene"
    string2 = "Cosmin si Andrei merg cu bicicleta ziua"
    assert get_last(string1) == "si"
    assert get_last(string2) == "ziua"

    start_time = time.process_time()
    get_last("Ana are mere rosii si galbene")
    end_time = time.process_time()
    print(
        "Timpul de execuție pentru funcția get_last:",
        end_time - start_time,
        "secunde",
    )


test()


def ultimul_cuvant(text):
    cuvinte = text.split()
    ultimul_cuvant = max(cuvinte)
    return ultimul_cuvant


def test_ultimul_cuvant():
    assert ultimul_cuvant("Ana are mere rosii si galbene") == "si"
    assert ultimul_cuvant("Azi este o zi frumoasa") == "zi"
    assert ultimul_cuvant("Cine vine primul?") == "vine"


def testBot():
    # Testează funcția
    test_ultimul_cuvant()

    # Măsoară timpul de execuție pentru funcția ultimul_cuvant
    start_time = time.process_time()
    ultimul_cuvant("Ana are mere rosii si galbene")
    end_time = time.process_time()
    print(
        "Timpul de execuție pentru funcția ultimul_cuvant:",
        end_time - start_time,
        "secunde",
    )


testBot()
