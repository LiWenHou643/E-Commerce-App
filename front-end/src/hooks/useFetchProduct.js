// hooks/useProducts.js
import { useQuery } from '@tanstack/react-query';
import axiosInstance from '../api/axiosInstance';

const fetchProductById = async ({ queryKey }) => {
    const [_key, { productId }] = queryKey;
    const response = await axiosInstance.get(`/products/${productId}`);
    return response.data?.data; // Axios automatically parses JSON
};

const useFetchProduct = ({ productId }) => {
    return useQuery(['product', { productId }], fetchProductById, {
        // Keep previous data when fetching new data
        keepPreviousData: true, // Avoid loading state when changing pages

        // Refetch data on window focus
        refetchOnWindowFocus: false, // Avoid refetching data on window focus

        // Refetch data on network reconnect
        refetchOnReconnect: false, // Avoid refetching data on network reconnect

        // Refetch data on interval
        refetchInterval: false, // Avoid refetching data on interval

        // Refetch data on mount
        refetchOnMount: false, // Avoid refetching data on mount

        // Stale time
        staleTime: 30000, // Set the stale time to 30 seconds
    });
};

export default useFetchProduct;