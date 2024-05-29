import React, { useEffect, useRef, useState } from "react";
import SelectGroupOne from "../../components/Forms/SelectGroup/SelectGroupOne";

const VoucherModal: React.FC = () => {
    const [modalOpen, setModalOpen] = useState(false);

    const trigger = useRef<HTMLButtonElement>(null);
    const modal = useRef<HTMLDivElement>(null);

    // close on click outside
    useEffect(() => {
        const clickHandler = (event: MouseEvent) => {
            if (!modal.current || !trigger.current) return;
            if (
                !modalOpen ||
                modal.current.contains(event.target as Node) ||
                trigger.current.contains(event.target as Node)
            )
                return;
            setModalOpen(false);
        };
        document.addEventListener("click", clickHandler);
        return () => document.removeEventListener("click", clickHandler);
    }, [modalOpen]);

    // close if the esc key is pressed
    useEffect(() => {
        const keyHandler = (event: KeyboardEvent) => {
            if (!modalOpen || event.keyCode !== 27) return;
            setModalOpen(false);
        };
        document.addEventListener("keydown", keyHandler);
        return () => document.removeEventListener("keydown", keyHandler);
    }, [modalOpen]);

    return (
        <>
            <div className="container mx-auto">
                <button
                    ref={trigger}
                    onClick={() => setModalOpen(true)}
                    className={`rounded-md bg-primary px-6 py-3 text-base font-medium text-white`}
                >
                    <i className="fa-solid fa-plus"></i>&ensp;
                    Thêm voucher
                </button>
                {modalOpen && (
                    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
                        <div
                            className="fixed inset-0"
                            onClick={() => setModalOpen(false)}
                        ></div>
                        <div
                            ref={modal}
                            className="relative w-full max-w-[800px] bg-white ml-70 mt-20 text-center dark:border-strokedark dark:bg-boxdark max-h-[80vh] overflow-y-auto"
                        >
                            <button
                                onClick={() => setModalOpen(false)}
                                className="absolute top-4 right-4 text-gray-500 hover:text-gray-700 dark:text-gray-300 dark:hover:text-gray-100"
                            >
                                <i className="fa-solid fa-xmark"></i>
                            </button>
                            <div className="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                                <div className="border-b border-stroke py-4 px-6.5 dark:border-strokedark">
                                    <h3 className="font-medium text-black dark:text-white">
                                        Thêm voucher
                                    </h3>
                                </div>
                                <form action="#">
                                    <div className="p-6.5">
                                        <div className="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                            <div className="w-full xl:w-1/2">
                                                <label className="mb-2.5 block text-black dark:text-white">
                                                    Tên sản phẩm
                                                </label>
                                                <input
                                                    type="text"
                                                    placeholder="Enter your first name"
                                                    className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                                />
                                            </div>

                                            <div className="w-full xl:w-1/2">
                                                <label className="mb-2.5 block text-black dark:text-white">
                                                    Giá
                                                </label>
                                                <input
                                                    type="text"
                                                    placeholder="Enter your last name"
                                                    className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                                />
                                            </div>
                                        </div>

                                        <div className="mb-4.5">
                                            <label className="mb-2.5 block text-black dark:text-white">
                                                Phân loại 1
                                            </label>
                                            <input
                                                type="email"
                                                placeholder="Enter your email address"
                                                className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                            />
                                        </div>

                                        <div className="mb-4.5">
                                            <label className="mb-2.5 block text-black dark:text-white">
                                                Phân loại 2
                                            </label>
                                            <input
                                                type="email"
                                                placeholder="Enter your email address"
                                                className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                            />
                                        </div>

                                        <SelectGroupOne />

                                        <div className="mb-4.5">
                                            <label className="mb-2.5 block text-black dark:text-white">
                                                Số lượng
                                            </label>
                                            <input
                                                type="text"
                                                placeholder="Select subject"
                                                className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                            />
                                        </div>

                                        <div className="mb-6">
                                            <label className="mb-2.5 block text-black dark:text-white">
                                                Mô tả
                                            </label>
                                            <textarea
                                                rows={6}
                                                placeholder="Nhập mô tả sản phẩm của bạn..."
                                                className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                            ></textarea>
                                        </div>

                                        <button className="flex w-full justify-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
                                            Send Message
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
};

export default VoucherModal;
