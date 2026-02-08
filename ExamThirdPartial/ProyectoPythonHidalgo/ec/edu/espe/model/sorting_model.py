from abc import ABC, abstractmethod

# 1. La Interfaz (Strategy)
class SortingStrategy(ABC):
    @abstractmethod
    def sort(self, data):
        pass

# 2. Estrategia Concreta: Bubble Sort
class BubbleSort(SortingStrategy):
    def sort(self, data):
        arr = data.copy()
        n = len(arr)
        for i in range(n - 1):
            for j in range(n - i - 1):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
        return arr

# 3. Estrategia Concreta: Insertion Sort
class InsertionSort(SortingStrategy):
    def sort(self, data):
        arr = data.copy()
        for i in range(1, len(arr)):
            key = arr[i]
            j = i - 1
            while j >= 0 and key < arr[j]:
                arr[j + 1] = arr[j]
                j -= 1
            arr[j + 1] = key
        return arr

# 4. Estrategia Concreta: Quick Sort
class QuickSort(SortingStrategy):
    def sort(self, data):
        arr = data.copy()
        self._quick_sort_recursive(arr, 0, len(arr) - 1)
        return arr

    def _quick_sort_recursive(self, arr, low, high):
        if low < high:
            pi = self._partition(arr, low, high)
            self._quick_sort_recursive(arr, low, pi - 1)
            self._quick_sort_recursive(arr, pi + 1, high)

    def _partition(self, arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            if arr[j] <= pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        return i + 1

# 5. El Contexto
class SortingContext:
    def __init__(self):
        self._strategy = None

    def set_sort_strategy(self, strategy):
        self._strategy = strategy

    def sort(self, data):
        if not self._strategy:
            raise Exception("Estrategia no definida")
        return self._strategy.sort(data)

    def get_strategy_name(self):
        return self._strategy.__class__.__name__