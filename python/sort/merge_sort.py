from typing import List


class Solution:
    def mergeSort(self,A: List[int], start: int, end: int):
        if start == end:
            return
        mid = (start + end) // 2
        self.mergeSort(A, start, mid)
        self.mergeSort(A, mid + 1, end)
        self.mergeSort2(A, start, end)

    def mergeSort2(self,A: List[int], start: int, end: int):
        end1 = (start + end) // 2
        start2 = end1 + 1
        idx1 = start, idx2 = start2
        while idx1 <= end1 and idx2 <= end:
            if A[idx1] < A[idx2]:
                idx1 += 1
            else:
                val = A[idx2]
                index = idx2
                while index > idx1:
                    A[index + 1] = A[index]
                    index -= 1
                A[index] = val
                idx1 += 1
                idx2 += 1
                end1 += 1

    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        """
        Do not return anything, modify A in-place instead.
        """
        len_A = len(A)
        if len_A < 2:
            return A
        j = 0
        for i in range(m, len_A):
            A[i] = B[j]
            j += 1

        self.mergeSort(A, 0, len_A - 1)
        return A

