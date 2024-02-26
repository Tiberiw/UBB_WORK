"""
Să se determine distanța Euclideană între două locații identificate prin perechi de numere.
De ex. distanța între (1,5) și (4,1) este 5.0
"""

import math


class Point:
    def __init__(self, first, second):
        self.first = first
        self.second = second

    def getDistance(self, other):
        return math.dist([self.first, self.second], [other.first, other.second])


def test():
    A = Point(1, 5)
    B = Point(4, 1)
    print(A.getDistance(B))


test()
