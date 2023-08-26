import { configureStore } from '@reduxjs/toolkit'
import authSlice from './features/authSlice'
import cartSlice from './features/cartSlice'
import cartReducer from './features/cartSlice'
import storage from 'redux-persist/lib/storage/session'; 
import { persistStore, persistReducer } from 'redux-persist';

const persistConfig = {
  key: 'root',
  storage,
};

const persistedCartReducer = persistReducer(persistConfig, cartReducer);

// create a new store
export const store = configureStore({
  reducer: {
    auth: authSlice,
    cart: persistedCartReducer,
    // cart: cartSlice,
  },
})

export const persistor = persistStore(store);

