def kth_smallest(array, k):
    array.sort(reverse=True)
    return array[k - 1]


def test():
    assert kth_smallest([7, 4, 6, 3, 9, 1], 2) == 7
    assert kth_smallest([1, 2, 3, 9, 9, 4, 5], 3) == 5
    assert kth_smallest([1, 1, 5, 4, 3, 5, 5, 7], 1) == 7


test()
